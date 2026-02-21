module Lab13 where
import Data.Char
import Data.Function

--hajtogtasok: fold :: ( a -> a -> a ) -> a -> [a] -> a

--hajtogatas jobbrol
foldr :: ( a -> b -> b ) -> b -> [a] -> b
foldr f e [] = e
foldr f e (x:xs) =x `f` foldr f e xs

--hajtogatas balrol
foldl :: ( a -> b -> b ) -> b -> [a] -> b
foldl f e [] = e
foldl f e (x:xs) =foldl f (e `f` x) xs

--FELADATOK

myAnd :: [Bool] -> Bool
myAnd = foldr (&&) True 

myMaximum :: Ord a => [a] -> a 
myMaximum (x:xs) = foldl max x xs

myMaximum' :: Ord a => [a] -> a 
myMaximum' = foldl1 max 

myFlip :: ( a -> b -> c ) -> b -> a -> c
myFlip f x y = f y x

--on fv : Data.Function modul-bol jelenitheto meg, ket elem osszehasonlitasa 

groupBy :: (a -> a -> Bool) -> [a] -> [[a]]

numbersInString :: String -> [String]
numbersInString = filter(isDigit . head) . groupBy (\x y -> isDigit x && isDigit y) 