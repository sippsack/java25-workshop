package java25update;

public class ManySmallObjects {
  // Maximum memory needed:
  // - Array:   ARRAY_SIZE * 4 bytes
  // - Objects: ARRAY_SIZE * 24 bytes *without* compressed object headers
  //            ARRAY_SIZE * 16 bytes *with* compressed object headers
  // - Total:   ARRAY_SIZE * 28 bytes *without* compressed object headers
  //            ARRAY_SIZE * 20 bytes *with* compressed object headers

  // Expectation: With an array size of 500,000,000, ...
  // ... we need 14,000,000,000 bytes of memory *without* compact object headers
  // ...     and 10,000,000,000 bytes of memory *with* compact object headers.
  private static final int ARRAY_SIZE = 500_000_000;

  private final Long[] array = new Long[ARRAY_SIZE];

  void main() {
    IO.println("Filling array...");
    fillArray();

    IO.println("Array filled; calculating sum...");
    calculateSum();

    IO.readln("Press ENTER to continue");
  }

  private void fillArray() {
    for (int i = 0; i < ARRAY_SIZE; i++) {
      array[i] = Long.valueOf(i);
    }
  }

  private void calculateSum() {
    long sum = 0;
    for (long l : array) {
      sum += l;
    }
    IO.println("The sum is: " + sum);
  }
}
