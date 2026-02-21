module MZH1 where

import Data.Char

doubleTriple :: [a] -> [a]
doubleTriple []=[]
doubleTriple [x]= [x,x,x]
doubleTriple [x,y]=[x,x,y,y]
doubleTriple x =x

lengthOfShorter :: [a] -> [b] -> Integer
lengthOfShorter a b = lenH a b 0
    where 
        lenH [] _ n =n
        lenH _ [] n =n    
        lenH (x:xs) (y:ys) n=lenH xs ys (n+1)

compressLetters :: String -> String
compressLetters [] =[]
compressLetters [x]=[x]
compressLetters (x:y:xs) 
    | isLower x && isLower y && x == y = toUpper x : compressLetters xs
    | otherwise = x : compressLetters (y:xs)