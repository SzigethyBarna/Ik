module Gyak11 where
import Data.Char

addTwoToWholeList :: Num a => [a] -> [a]
{-addTwoToWholeList [] = []
addTwoToWholeList  (x:xs) = x+2 : addTwoToWholeList xs -}
addTwoToWholeList xs = map (+2) xs

toUpperAll :: String -> String
{-toUpperAll [] = []
toUpperAll (x:xs) = toUpper x: toUpperAll xs-}
toUpperAll xs = map toUpper xs

negateAll :: [Bool] -> [Bool]
{-negateAll [] = []
negateAll (x:xs) = not x : negateAll xs-}
--negateAll xs = map not xs
negateAll = map not

keepEven :: (Num a,Integral a) => [a] -> [a]
--keepEven []= []
{-keepEven (x:xs)
    |even x = x: keepEven xs
    |otherwise = keepEven xs-}
keepEven xs = filter (even) xs
 
keepHigherThanThree :: (Integral a, Ord a) => [a] -> [a]
{-keepHigherThanThree []=[]
keepHigherThanThree (x:xs)
    |x>3= x : keepHigherThanThree xs
    |otherwise = keepHigherThanThree xs-}
keepHigherThanThree xs = filter (>3) xs

keepUppers :: String -> String
{-keepUppers [] = []
keepUppers (x:xs)
    |isUpper x= x:isUpper xs
    |otherwise = isUpper xs-}
keepUppers xs = filter (isUpper) xs

--map fv
myMap :: (a->b) ->[a]->[b]
myMap f [] = []
myMap f (x:xs) = f x : myMap f xs

--filter fv
myFilter :: ( a->Bool ) ->[a]->[b]
myFilter p [] = []
myFilter p (x:xs)
    |p x=x:myFilter xs
    |otherwise myFilter xs

--Magasabb rendű függvények 

--Currying - Haskell Curry (matematikus)

--LAMBDA KIFEJEZÉSEK
--szintaxis: (\x y -> x+y), (\x ->x+1) 

--Lokális definíciók

myZipWith :: (a -> b -> c) ->[a] ->[b] ->[c]
myZipWith f [] []= []
myZipWith f _ []= []
myZipWith f [] _= []
myZipWith f (x:xs) (y:ys)= f x y :myZipWith f xs ys

myAll :: (a -> Bool)->[a] -> Bool
myAll p [] = True
myAll p (x:xs)
    | p x = myAll p xs
    |otherwise = False

myAny :: (a -> Bool)->[a] -> Bool
myAny p [] = False
myAny p (x:xs)
    |p x = True
    |otherwise = myAll p xs

filters :: Eq a => [a] -> [a] -> [a]
filters filteringElements xs = filter (`notElem` filteringElements) xs

compress :: [a] ->[(a,Int)]
compress lst = map (\g ->(head g, length g)) (Group lst)
