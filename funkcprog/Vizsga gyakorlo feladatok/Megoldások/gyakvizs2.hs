module Vizs2 where
import Data.Char
import Data.List

whichIsEmpty :: [a] -> [b] -> Maybe Int
whichIsEmpty [] _=Just 1
whichIsEmpty _ []=Just 2
whichIsEmpty _ _=Nothing

match :: Eq a => (a,a) -> (a, a) -> Bool
match (x,y) (z,w)
    |x==z || x==w || y==z || y==w =True
    |otherwise=False

indicesOfEmpties :: Eq a => [[a]] -> [Int]
indicesOfEmpties []=[]
indicesOfEmpties ls = helper ls 1
    where
        helper [] _ =[]
        helper (x:xs) n
            |x==[]= n:helper xs (n+1)
            |otherwise= helper xs (n+1)

applyOnWords :: (String -> String) -> String -> String
applyOnWords f s=unwords (map f (words s))

restUntil :: (a -> [a] -> Bool) -> [a] -> [a]
restUntil _ [] =[]
restUntil f (x:xs)
    |f x xs =x:xs
    |otherwise= restUntil f xs

replaceAll :: Eq a => a {-mit-} -> [a] -> a {-mire-} -> [a]
replaceAll _ [] _=[]
replaceAll a (x:xs) b
    |x==a=b:replaceAll a xs b
    |otherwise= x:replaceAll a xs b

listWordsWithUpper :: String -> [String]
listWordsWithUpper ls=helper (words ls)
    where
        helper [] = []
        helper (x:xs)
            |any isUpper x = x:helper xs
            |otherwise= helper xs

