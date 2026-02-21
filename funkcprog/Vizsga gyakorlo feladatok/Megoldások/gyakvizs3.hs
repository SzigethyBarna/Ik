module Gyakvisz3 where
import Data.List
import Data.Char

onlyEvens :: Integral a => [a] -> [a]
onlyEvens []=[]
onlyEvens (x:xs)
    |x `div` 2==0=x:onlyEvens xs
    |otherwise= onlyEvens xs

countWordsWith :: Char -> String -> Int
countWordsWith _ ""=0
countWordsWith b ls = helper b (words ls) 0
    where
        helper b [] n = n 
        helper b (x:xs) n
            |b `elem` x = helper b xs n+1
            |otherwise= helper b xs n

takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' f []=[]
takeWhile' f (x:xs) 
    |f x = x:takeWhile' f xs
    |otherwise= []

firstGreater :: Ord a => a -> [a] -> Maybe a
firstGreater _ [] = Nothing
firstGreater n (x:xs)
    |x>n=Just x
    |otherwise=firstGreater n xs

wordLengths :: String -> [Int]
wordLengths ""=[]
wordLengths ls= helper (words ls)
    where
        helper []=[]
        helper (x:xs) = length x: helper xs 

everySecond :: [a] -> [a]
everySecond []=[]
everySecond [x]=[]
everySecond (x:y:xs)=y:everySecond xs

isEmpty:: [a] -> Bool
isEmpty []=True


removeEmpties :: [[a]] -> [[a]]
removeEmpties []=[]
removeEmpties (x:xs)
    |isEmpty x = removeEmpties xs
    |otherwise= x: removeEmpties xs
