Serializacja wartości opcjonalnych w ICE

Treść IDL:
```slice
#ifndef EXAMPLE_ICE
#define EXAMPLE_ICE

module Example
{
  class ClassWithOptionalField {
	optional(1) int optionalField;
	int required;
  }
  exception ExceptionWithOptionalValue {
  	optional(1) string why;
  }
  interface ExampleInterface
  {
  	optional(3) int methodWithOptionalReturnAndSomeArgs(optional(1) int arg1, int arg2);
	void methodThrowingExceptionWithOptionalValue(bool hasWhy) throws ExceptionWithOptionalValue;
	int methodAcceptingClassWithOptionalValues(ClassWithOptionalField arg);
  };

};

#endif
```

---
W celu zdefiniowania pola opcjonalnego podaje się słowo kluczowe optional z tagiem numerycznym ktory musi być unikalny w obrębie scopea

struktury nie moga zawierać pól opcjonalnych

Zgodnie z [dokumentacją](https://doc.zeroc.com/ice/3.7/best-practices/optional-values#id-.OptionalValuesv3.7-overhead) na narzut związany z przesyłem wartości opcjonalnej wpływają typ pola i wartość tagu

różne wartości tagów potrzebują rózne ilości dodatkowego miejsca:

wartość tagu | dodatkowe potrzebne bajty
---   | ---
0-29  |0  
30-254|1
255-  |5

ilość dodatkowego miejsca zależy też od typu pola:

typ | dodatkowe bajty
--- | ---
typy prymitywne (w tym string) | 1
Objekt | 1
sekwencja bajtów lub booli | 1
enumerator | 1
sekwencja | 2-5
struktura | 2-5
słownik | 2-5

wartości opcjonalne nie zajmują żadnego miejsca, jeśli ich nie ma, natomiast w przypadku, w którym są zajmują trochę więcej miejsca niż gdyby nie były opcjonalne


---

Wyjście z programu clienta:
```
methodWithOptionalReturnAndSomeArgs(OptionalInt.empty(), 5) returned
OptionalInt.empty
methodWithOptionalReturnAndSomeArgs(OptionalInt.of(3), 7) returned
OptionalInt[10]
methodThrowingExceptionWithOptionalValue(false) threw
Optional.empty
methodThrowingExceptionWithOptionalValue(true) threw
Optional[why field is present]
methodAcceptingClassWithOptionalValues(new ClassWithOptionalField(0)) returned
0
methodAcceptingClassWithOptionalValues(new ClassWithOptionalField(6, 1)) returned
7
```
---
---
korelując dane zawarte w [ice.pcapng](./ice.pcapng) z wyjściem programu klienckiego i wyciągając przesyłane dane otrzymujemy następujące zależności

wszystkie parametry i wartości zwracane są zawsze opakowywane w enkapsulacje która składa się z 6 bajtów na początku, (4 bajty przechowujące wielkość całej enkapsulacji i po bajcie na wersje major i minor), jej wytłumaczenie zostanie pominięte w poniższych analizach

---

rozpoczęcie wywołania methodWithOptionalReturnAndSomeArgs(OptionalInt.empty(), 5) przesłało następujące dane:

enkapsulacja | arg 1
---| ---
0a 00 00 00 01 01|05 00 00 00

jak widać zawiera się w tym jedynie wartość 5, argument opcjonalny nie został uwzględniony

server odpowiedział następującymi bajtami danych

enkapsulacja |
---
06 00 00 00 01 01 |

czyli pustą enkapsulacją

---
rozpoczęcie wywołania methodWithOptionalReturnAndSomeArgs(OptionalInt.of(3), 7)

przesłało następujące dane:
enkapsulacja | argument wymagany | dodatkowy bajt | wartość argumentu opcjonalnego
--- | --- | ---| ---
0f 00 00 00 01 01 | 07 00 00 00 | 0a | 03 00 00 00

jak widać przesłanie wartości spowodował dodanie bajtu

po rozbiciu tego bajta na bity zobaczymy:
typ wartości opcjonalnej | tag lub 30 w przypadku gdy tag jest większy od 29 (wtedy pojawi się kolejny bajt)
--- | ---
00001|010

typ 2 oznacza wartość zakodowaną 4 bajtami
wraz z tagiem = 1

server odpowiedział następującymi bajtami danych
enkapsulacja | dodatkowy bajt | wartość zwracana
--- | --- | ---
0b 00 00 00 01 01| 1a |0a 00 00 00

dodatkowy bajt rozkłada się analogicznie, z tym że tag = 3

---
---

analiza wysłanych bajtów dla methodThrowingExceptionWithOptionalValue(false) pomięta, gdyż nie zawiera ono wartości opcjonalnych

serwer odpowiedział następującymi bajtami:
zwrócił user exception z danymi równymi:

enkapsulacja | flagi (last slice) | type name size | type name (37 bajtów)
---| --- | --- | ---
2d 00 00 00 01 01 | 20 | 25 | 3a 3a 45 78 61 6d 70 6c 65 3a 3a 45 78 63 65 70 74 69 6f 6e 57 69 74 68 4f 70 74 69 6f 6e 61 6c 56 61 6c 75 65

type name to: `::Example::ExceptionWithOptionalValue`

---
analiza wysłanych bajtów dla methodThrowingExceptionWithOptionalValue(true) pomięta, gdyż nie zawiera ono wartości opcjonalnych

serwer odpowiedział następującymi bajtami:
zwrócił user exception z danymi równymi:

enkapsulacja | flagi (last slice + has optional data) | type name size | type name (37 bajtów) | dodatkowy bajt (tag = 1, type =Vsize (string)) | Vsize size | optional string value (20 bajtów) | end marker
--- | --- | --- | --- | --- | --- | --- | ---
44 00 00 00 01 01 |24 | 25 | 3a 3a 45 78 61 6d 70 6c 65 3a 3a 45 78 63 65 70 74 69 6f 6e 57 69 74 68 4f 70 74 69 6f 6e 61 6c 56 61 6c 75 65 | 0d | 14 |77 68 79 20 66 69 65 6c 64 20 69 73 20 70 72 65 73 65 6e 74 | ff

type name to: `::Example::ExceptionWithOptionalValue`

optional string value to: `why field is present`

---
---

rozpoczęcie wysłania methodAcceptingClassWithOptionalValues(new ClassWithOptionalField(0))

przesłało następujące dane:
enkapsulacja | identity marker (1) | flagi(last slice + typeid jako string) | type name size | type name (33 bajty) | required field (0)
--- | --- | --- | --- | ---| --- |
2e 00 00 00 01 01| 01 |  21| 21 | 3a 3a 45 78 61 6d 70 6c 65 3a 3a 43 6c 61 73 73 57 69 74 68 4f 70 74 69 6f 6e 61 6c 46 69 65 6c 64 | 00 00 00 00

odpowiedź zostanie pominięta jako że nie zawiera wartości opcjonalnych

---

rozpoczęcie wysłania methodAcceptingClassWithOptionalValues(new ClassWithOptionalField(6, 1))

przesłało następujące dane:
enkapsulacja | identity marker (1) | flagi(last slice + optional data members + typeid jako string) | type name size | type name (33 bajty) | required field (1) | dodatkowy bajt (tag= 1, type= 4 bajtowy) | optional field value (6) | end marker
--- | --- | --- | --- | ---| --- |  --- | --- | ---
34 00 00 00 01 01 | 01 | 25 | 21 | 3a 3a 45 78 61 6d 70 6c 65 3a 3a 43 6c 61 73 73 57 69 74 68 4f 70 74 69 6f 6e 61 6c 46 69 65 6c 64 | 01 00 00 00 | 0a | 06 00 00 00 | ff 



---
---

wartości opcjonalne w ICE są przechowywane tak samo nie zależnie od tego w jakim miejscu się znajdują, tzn. dodawany jest dodatkowy(e) bajt(y) opisujące jaka jest wielkość typu i jego tag
dodatkowo jeżeli wartości opcjonalne pojawiają się w slice-ach to musi być ustawiona odpowiednia flaga i sekcja wartości opcjonalnych powina się zakończyć end markerem (255)

---
---

Wnioski:

jako, że oznaczenie pola jako opcjonalne powoduje, że zużywają one więcej miejsca w trakcie transportu powinniśmy ich raczej używać w sytuacjach, w których taka wartość będzie rzadko wypełniona