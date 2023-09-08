# Grass: data storage library

Grass is a simple library that allows you to easily create different ways of storing data.


## Explanation of classes and read / write steps.

1. A data input is provided, could be a file, socket or something else.
2. The input is loaded into a ByteModifier by an InputModifier so it can be modified by Grass easily. (The InputModifier allows the ByteModifier to modify the input)
3. A MasterTransform is created and allows to read and write Tags from and to the input via the ByteModifier.
4. The MasterTransform uses TypeTransforms to transform data from the input into tags.
5. If there is no TypeTransform for the type of data then it uses a DefaultTransform that should require the class to have an empty constructor.


## Warning

I don't know any naming standards or structures of libraries, so I just named classes and packages how I thought it fit their use best.
