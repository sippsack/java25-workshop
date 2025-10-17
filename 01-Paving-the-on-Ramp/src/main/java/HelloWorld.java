void main() {
    IO.println("Hello, World!");
}

// > java HelloWorld.java Falk
void main(String[] args) {
    IO.println(String.format("Hello, %s!", args.length > 0 ? args[0] : "World"));
}