Serializacja wartości opcjonalnych w GRPC

Treść IDL:
```proto
syntax = "proto3";

option java_multiple_files = true;
option java_package = "sr.middleware.gen";
option java_outer_classname = "ExampleProto";
option csharp_namespace = "ExampleProto";

package example;

message MessageWithOptionalField {
	optional int32 arg1 = 1;
	int32 arg2 = 2;
}

message MessageWithoutOptionalField {
	int32 arg1 = 1;
	int32 arg2 = 2;
}

message MessageReturned {
	int32 return = 1;
}

service Example {
	rpc MethodWithOptionalArgs(MessageWithOptionalField) returns (MessageReturned) {}
	rpc MethodWithoutOptionalArgs(MessageWithoutOptionalField) returns (MessageReturned) {}
}
```
---

W protocol buffer wersji 3 nie istnieje coś takiego jak wartość wymagana, została ona usunięta i wszystkie wartości są opcjonalne

Zgodnie z [dokumentacją](https://github.com/protocolbuffers/protobuf/blob/main/docs/field_presence.md) słowo kluczowe optional ma teraz nowe znaczenie

w normalnym przypadku, w celu zaoszczędzenia miejsca protocol buffers enkoduje wartości domyślne pól w ten sam sposób jakby pola nie było, przez co nie jest możliwe odróżnienie wartości brakującej od domyślniej

w przypadku oznaczenia pola jako optional protocol buffer enkoduje wartość domyślną jak każdą inną, pozwalając na odróżnienie sytuacji, w której wartość została pominięta od wartości domyślnej

---

Wyjście z programu clienta:
```
MethodWithOptionalArgs(new MessageWithOptionalField{Arg1 = 0, Arg2 = 5}) returned
{ "return": 5 }
MethodWithOptionalArgs(new MessageWithOptionalField{Arg2 = 5}) returned
{ "return": 1005 }
MethodWithoutOptionalArgs(new MessageWithoutOptionalField{Arg1 = 0, Arg2 = 5}) returned
{ "return": 5 }
MethodWithoutOptionalArgs(new MessageWithoutOptionalField{Arg2 = 5}) returned
{ "return": 5 }

```

---

korelując dane zawarte w [grpc.pcapng](./grpc.pcapng) z wyjściem programu klienckiego i wyciągając przesyłane dane otrzymujemy następujące zależności
(wartości zwracane zostaną pominięte jako, że nie zawierają żadnych pól oznaczonych jako opcjonalne)

---
wywołanie MethodWithOptionalArgs(new MessageWithOptionalField{Arg1 = 0, Arg2 = 5})
wysłało następujące dane:

`08 00 10 05`

co dekoduje się jako 2 pary key-value (4 varinty)

key1   (08) = 0 0001 000 -> wire_type = varint, tag = 1  

value1 (03) = 0 000 0000 ->  = 0

key2   (10) = 0 0010 000 -> wire_type = varint, tag = 2

value2 (05) = 0 000 0101 ->  = 5 

---

wywołanie MethodWithOptionalArgs(new MessageWithOptionalField{Arg2 = 5})
wysłało następujące dane:

`10 05`

co dekoduje się jako jedna para key-value (2 varinty)

key2   (10) = 0 0010 000 -> wire_type = varint, tag = 2

value2 (05) = 0 000 0101 ->  = 5 

---

wywołanie MethodWithoutOptionalArgs(new MessageWithoutOptionalField{Arg1 = 0, Arg2 = 5})
wysłało następujące dane:

`10 05`

co dekoduje się jako jedna para key-value (2 varinty)

key2   (10) = 0 0010 000 -> wire_type = varint, tag = 2

value2 (05) = 0 000 0101 ->  = 5 

---

wywołanie MethodWithoutOptionalArgs(new MessageWithoutOptionalField{Arg2 = 5})
wysłało następujące dane:

`10 05`

co dekoduje się jako jedna para key-value (2 varinty)

key2   (10) = 0 0010 000 -> wire_type = varint, tag = 2

value2 (05) = 0 000 0101 ->  = 5 


---
---

jak widać dodanie optional powoduje, że wartość domyślna zostanie potraktowana dokładnie tak samo jak inne wartości i zostanie uwzględniona w wysyłanym komunikacie


---
---
dodatkowe informacje: dekodowanie danych z wiadomości zwrotnych

dla {returned = 5}:

`08 05`
co dekoduje się jako jedna para key-value (2 varinty)

key2   (08) = 0 0001 000 -> wire_type = varint, tag = 1

value2 (05) = 0 000 0101 ->  = 5 


oraz dla {returned = 1005}:

`08 ed 07`
co dekoduje się jako jedna para key-value (2 varinty)

key2      (08) =             0 0001 000 -> wire_type = varint, tag = 1

value2 (ed 07) = 1 110 1101, 0 000 0111 ->  = 1005 

gdzie 1 jako MSB w pierwszym bajcie jest bitem kontynuacji


---
---

Wnioski:

w grpc słowo kluczowe optional powinniśmy używać wtedy gdy potrzebujemy wiedzieć czy dane pole zostało ustawione z domyślną wartością, czy pominięte

nie użycie tego słowa kluczowego powoduje, że niektóre wiadomości mogą być potencjalnie mniejsze od innych tego samego typu