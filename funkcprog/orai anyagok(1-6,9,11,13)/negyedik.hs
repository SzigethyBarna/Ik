module Negyedik where
import Data.Char (isUpper, isLower, toUpper, toLower)

descending :: Integer -> [Integer]
descending x = [x, x-1..(-1)*x]

divByN :: Integer -> [Integer]
divByN n
    |n>0 = [n,n+n..]
    |n<0=[]

ithInSeq :: Integer -> Integer -> Int -> Integer
ithInSeq a b c = [a,b..]!!(c-1)

nthLetter :: Int -> Bool -> Char
nthLetter a b
    |b==False = ['a' .. 'z']!!(a-1)
    |otherwise = ['A' .. 'Z']!!(a-1)

fact :: Integer -> Integer
fact a
    |a==0 = 1
    |a>0 = product[1..(a)]

perm :: Integer -> Integer
perm a
    |a==0 = 1
    |a>0 = product[1..(a)]

comb :: Integer -> Integer -> Integer
comb a b = div (product[1..(a)]) ((product[1..(a-b)])*(product[1..(b)]))

startsWithOne :: [Integer] -> Bool
startsWithOne (1:_) =True
startsWithOne _ =False

hd :: [a] -> a
hd (x:xs)= x

tl :: [a] -> [a]
tl (x:xs)= xs

toUpperFirst :: String -> String
toUpperFirst (x:xs) = (toUpper x:xs)
toUpperFirst _ = []  

myProduct :: Num a => [a] -> a
myProduct []= 1
myProduct(x:xs)= x * myProduct xs

myLast :: [a] -> a
myLast (x:[]) = x
myLast (_:xs) = myLast xs

myMinimum :: [Integer] -> Integer
myMinimum (x:[]) = x
myMinimum (x:xs) = min x (myMinimum xs)

myDrop :: Int -> [a] -> [a]
myDrop n []= []
myDrop n (x:xs)
    |n<=0 = (x:xs)
    |otherwise = myDrop (n-1) xs

myDrop2 :: Int -> [a] -> [a]
myDrop2 n xs
    | n<=0 = xs
myDrop2 _ [] = []
myDrop2 n (x:xs) = myDrop2 (n-1) xs

--nem jo
myElem :: Eq a => a -> [a] -> Bool
myElem n [] =False
myElem n [n]= True
myElem n (x:xs) = myElem n xs

