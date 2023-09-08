# Grass: data storage library

Grass is a simple library that allows you to easily create
different ways of storing data.

## Steps to read / write data

The steps that Grass follows are simple:
- Data input: could be a file, socket, or something else.
- ByteModifier: loads the data from the input.

1. A data input is provided, could be a file, socket or something else.
2. The input is loaded into a ByteModifier so it can be modified by Grass easily.
3. 