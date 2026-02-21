module Gyak where

import Data.Char

doubleTriple :: [a] -> [a]
doubleTriple []=[]
doubleTriple [x] = [x,x,x]
doubleTriple [x,y] =[x,x,y,y]
doubleTriple x =x

lengthOfShorter :: [a] -> [b] -> Integer
lengthOfShorter [] _ = 0
lengthOfShorter _ [] = 0
lengthOfShorter (x:xs) (y:ys) = lenH (x:xs) (y:ys) 0
    where
        lenH [] _ a =a
        lenH _ [] a =a
        lenH (x:xs) (y:ys) a = lenH xs ys (a+1)
    
compressLetters :: String -> String
compressLetters [] = []
compressLetters [x] = [x]
compressLetters (x:y:xs)
    | x == y && isLower x = toUpper x : compressLetters xs
    | otherwise = x : compressLetters (y:xs)

remdup :: Eq a => [a] -> [a]
remdup [] =[]
remdup (x:y:xs)
    |x==y = remdup (y:xs)
    |otherwise = x: remdup (y:xs)

deleteEveryThird :: [a] -> [a]
deleteEveryThird [] =[]
deleteEveryThird [x] =[x]
deleteEveryThird [x,y] =[x,y]
deleteEveryThird (x:y:z:xs) = x:y:deleteEveryThird xs

alternate :: [a] -> [a] -> [a]
alternate [] [] = []
alternate [x] [y] = [x]
alternate (x:y:xs) (a:b:as) = x : b : alternate xs as