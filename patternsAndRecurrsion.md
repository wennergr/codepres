# Playground for patterns and recurrion

**Find the length of a list** <br/>
scala> length(List(1,2,3,4,5)) <br/>
anw> 5

**Find the last element of a list.** <br/>
scala> last(List(1, 1, 2, 3, 5, 8))  <br/>
ans> 8

**Find the last but one element of a list.** <br/>
scala> penultimate(List(1, 1, 2, 3, 5, 8)) <br/>
ans> 5

**Take N element from a list** <br/>
scala> take(2, List(1,4,5,77,2,4,67,8,8)  <br/>
ans> List(1,4)

**Take elements while expression is true** <br/>
scala> takeWhile(List(1,2,3,4,5,6)( x => x < 4)  <br/>
ans> List(1,2,3)

**Drop N element from a list** <br/>
scala> takeWhile(List(1,2,3,4,5,6)( x => x < 4)  <br/>
ans> List(4,5,6)

**Extract a slice from a list.** <br/>
scala> slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))  <br/>
ans> List('d, 'e, 'f, 'g)

**Run a function on every element in a list.** <br/>
scala> map(List(1,2,3))( x => x * 2) <br/>
ans> List(2, 4, 6) <br/>

**Flatten a nested list structure.** <br/>
scala> flatten(List(List(1, 1), 2, List(3, List(5, 8)))) <br/>
ans> List(1, 1, 2, 3, 5, 8) <br/>

