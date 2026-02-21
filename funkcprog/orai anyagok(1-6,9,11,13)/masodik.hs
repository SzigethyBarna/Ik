module Masodik where

isEven :: Integer -> Bool
isEven x= mod x 2==0

isOdd :: Integer ->Bool
isOdd x= mod x 2==1

divides :: Integer -> Integer -> Bool
divides x y= mod y x==0

triangleSides :: Integer -> Integer -> Integer -> Bool
triangleSides x y z= x+y>z

isLeapYear :: Integer -> Bool
isLeapYear x = mod x 4==0

isZero :: Integer -> Bool
isZero 0 = True

mul3 :: Int -> Int -> Int
mul3 0 _=0
mul3 _ 0=0
mul3 1 x=x
mul3 x 1=x
mul3 2 2=2

divsOf60 :: [Integer]
divsOf60 = [x| x<-[1..60],x `divides` 60]

fact :: Integer -> Integer
fact 0=1
fact n=n*fact(n-1)

sumTo :: Integer -> Integer
sumTo 0=0
sumTo n=n+sumTo(n-1)