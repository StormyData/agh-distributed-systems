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
  interface ExampleInterface {
  	optional(3) int methodWithOptionalReturnAndSomeArgs(optional(1) int arg1, int arg2);
	void methodThrowingExceptionWithOptionalValue(bool hasWhy) throws ExceptionWithOptionalValue;
	int methodAcceptingClassWithOptionalValues(ClassWithOptionalField arg);
  };

};

#endif
