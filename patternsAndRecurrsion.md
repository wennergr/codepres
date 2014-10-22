# Playground for patterns and recurrion

## Find the length of a list
scala> length(List(1,2,3,4,5))
anw> 5

## Find the last element of a list.
scala> last(List(1, 1, 2, 3, 5, 8))
ans> 8

## Find the last but one element of a list.
ans> 5

## Take N element from a list
scala> take(2, List(1,4,5,77,2,4,67,8,8)
ans> List(1,4)

## Take elements while expression is true
scala> takeWhile(List(1,2,3,4,5,6)( x => x < 4)
ans> List(1,2,3)

## Drop N element from a list
scala> takeWhile(List(1,2,3,4,5,6)( x => x < 4)
ans> List(4,5,6)

## Extract a slice from a list.
scala> slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
ans> List('d, 'e, 'f, 'g)

