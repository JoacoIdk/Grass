# Grass: data storage library

Grass is a simple library that allows you to easily create different ways of storing data.


## Explanation of concepts

Conversion: It is a group of transforms with a similar format.
Transform: Class that changes tags into some other format.
MasterTransform: Transform that receives all the data and chooses the right TypeTransform to change the data.
TypeTransform: Transform that only accepts one type of tag.
DefaultTransform: Transform that accepts all types of tags that have an empty constructor but with a lower efficiency.
Modifier: Class that modifies data.
InputModifier: Modifier that accepts data from one type of input.
ByteModifier: Modifier that accepts data from bytes.
Tag: Class that contains a name as a string, type as a class and value as the type it contains.


## Read / write steps

1. A data input is provided, could be a file, socket or something else.
2. The input is loaded into a ByteModifier by an InputModifier, so it can be modified by Grass easily. (The InputModifier allows the ByteModifier to interact with the input)
3. A MasterTransform is created and allows to read and write Tags from and to the input via the ByteModifier.
4. The MasterTransform uses TypeTransforms to transform data from the input into tags.
5. If there is no TypeTransform for the type of data then it uses a DefaultTransform that should require the class to have an empty constructor.


## Examples

Examples will be a program that create a transform with a conversion configuration, modify a file with a cache input modifier, write a tag named "testObject" with a test object that contains 2 randomly generated ints, print the tags contained in the file and close the input modifier.

### Using compact conversion

```java
package me.zephi.grass;

import lombok.SneakyThrows;
import me.zephi.grass.modifier.input.CacheInputModifier;
import me.zephi.grass.modifier.input.FileInputModifier;
import me.zephi.grass.tag.MasterTransform;
import me.zephi.grass.tag.Tag;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class Example {

    @SneakyThrows
    public static void main(String[] args) {
        MasterTransform master = Grass.createCompactTransform();
        Path path = Path.of("./file.test");

        if (!Files.exists(path))
            Files.createFile(path);

        CacheInputModifier modifier = new CacheInputModifier(new FileInputModifier(master, path));

        Supplier<Integer> supplier = () -> ThreadLocalRandom.current().nextInt();

        TestObject testObject = new TestObject(supplier.get(), supplier.get());
        Tag<TestObject> testObjectTag = new Tag<>("testObject", TestObject.class, testObject);
        modifier.writeTag(testObjectTag);

        modifier.queryAll((t) -> true).forEach(System.out::println);

        modifier.close();
    }

}
```

## Warning

I don't know any naming standards or structures of libraries, so I just named classes and packages how I thought it fit their use best.
