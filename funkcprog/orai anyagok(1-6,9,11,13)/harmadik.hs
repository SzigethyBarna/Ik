module Harmadik where
import Data.Char (isUpper, isLower, toUpper, toLower)

sphereVolume ::Double -> Double
sphereVolume r=4*r^3*pi/3

min' :: Integer->Integer->Integer
min' x y
    | x<=y=x
min' x y=y

myAbs :: Integer -> Integer
myAbs x 
    |x<0 = x*(-1)
myAbs x = x

sign :: Integer -> Integer
sign x 
    |x<0=(-1)
    |x>0=1
    |x==0 = 0

fact :: Integer -> Integer
fact 0 = 1
fact n
    |n<0= n*fact(n+1)
    |n>0 = n * fact (n-1)


swapUpperLower :: Char -> Char
swapUpperLower x
    |isUpper x= toLower x
    |isLower x=toUpper x
    |otherwise = x

sumTo :: Integer -> Integer
sumTo n
    |n<=0=0
    |otherwise = n+sumTo(n-1)

sumHelper :: Integer -> Integer -> Integer
sumHelper n m
    |n==m = n
    |otherwise = n+ sumHelper (n+1) m

sumBetween :: Integer -> Integer -> Integer
sumBetween n m
    | n<=m sumHelper n m
    | otherwise =sumHelper m n

--sumSquaresFromTo :: Integer -> Integer -> Integer
--sumSquaresFromTo x y 
