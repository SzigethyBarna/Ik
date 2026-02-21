module Hatodik where

powersOf2 :: Int -> [Integer]
powersOf2 n= [2^i | i<-[0..n]]

oddEven :: Int -> [Bool]
oddEven n = [ mod i 2 ==0 | i  <- [1..n]]

myReplicate :: Int -> a -> [a]
myReplicate n y = [ y | i <- [1..n]  ]

powerOfTwo :: Integer -> Integer
powerOfTwo n= head[ 2^x |x<-[0..], 2^x>n ]

exponentOf2 :: Integer -> Integer
exponentOf2 n= head[ x |x<-[0..], 2^x>n ]

divisors :: Integer -> [Integer]
divisors n = [ i | i <- [1..n], n `mod` i == 0]

isPrime :: Integer -> Bool
isPrime n = length(divisors n)==2

time :: [(Int, Int)]
time= [(h,m) |h <- [0..23], m <-[0..59]] 

primesToN :: Integer -> [Integer]
primesToN n =[i | i<- [1..n], isPrime i]

dominoes :: Integer -> [(Integer, Integer)]
dominoes n = [(x,y) | x<-[0..n] ,y<-[0..n],y>=x]
