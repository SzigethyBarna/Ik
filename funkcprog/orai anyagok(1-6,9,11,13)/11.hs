module Lab11 where

import Data.Char

myZipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
myZipWith f (x:xs) (y:ys) =f x y : myZipWith f xs ys
myZipWith f _ _ = []

notNull :: [a] -> Bool
--notNull xs = (not . Null) xs
notNull = (not . Null) 

myEven ::Integral a=>a->Bool
myEven = (==0) . (`mod` 2) 

myIterate :: ( a -> a) -> a -> [a]
myIterate f x = x : myIterate f (f x)

numbersMadeOfOnes :: [Integer]
numbersMadeOfOnes =iterate (\x -> x*10 +1) 1

numbersMadeOfThrees :: [Integer]
numbersMadeOfThrees =iterate (\x -> x*10 +3) 3

dropSpaces :: String -> String
dropSpaces xs = dropWhile isSpace xs

trim :: String -> String
--trim xs = reverse $ dropSpaces $ reverse $ dropSpaces xs 
trim = reverse . dropSpaces . reverse . dropSpaces 

firstLetters :: String -> String
firstLetters xs= unwords $ map (take 1) $ words xs

monogram :: String -> String
monogram xs= unwords $ map (\ (x:_) -> x:".") $ words xs

