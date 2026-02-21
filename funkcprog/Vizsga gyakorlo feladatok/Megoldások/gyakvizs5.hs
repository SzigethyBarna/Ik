module Gyakvizs5 where
import Prelude hiding (Left, Right)
splitOnZero :: [Int] -> [[Int]]
splitOnZero xs= helper xs []
    where
        helper [] []=[]
        helper [] acc=[reverse acc]
        helper (y:ys) acc
            |y==0 =reverse acc:helper ys []
            |otherwise= helper ys (y:acc)

safeDivList :: [Int] -> [Int] -> [Maybe Int]
safeDivList [] _=[]
safeDivList _ []=[]
safeDivList (x:xs) (y:ys)
    |y==0=Nothing:safeDivList xs ys
    |otherwise= (Just (x `div` y)):safeDivList xs ys

localMaxima :: [Int] -> [Int]
localMaxima [] =[]
localMaxima [x]=[]
localMaxima [x,y]=[]
localMaxima (x:y:z:xs)
    |x<y && y>z=y: localMaxima (z:xs)
    |otherwise=localMaxima (y:z:xs)

interleave :: [a] -> [a] -> [a]
interleave [] []=[]
interleave [] (x:xs)=(x:xs)
interleave (x:xs) []=(x:xs)
interleave (x:xs) (y:ys)=x:y:interleave xs ys

data Move = Up | Down | Left | Right
          deriving (Show, Eq)

finalPosition :: [Move] -> (Int, Int)
finalPosition []=(0,0)
finalPosition (x:xs)=helper (x:xs) 0 0
    where
        helper [] x y=(x,y)
        helper (l:ls) x y=case l of
            Up->helper ls x (y+1)
            Down->helper ls x (y-1)
            Left -> helper ls (x-1) y
            Right -> helper ls (x+1) y

groupBySign :: [Int] -> [[Int]]
groupBySign []=[]
groupBySign (x:xs)=helper xs [x]
    where
        helper [] acc=[reverse acc]
        helper (y:ys) acc@(prev:_)
            |(y>=0)==(prev>=0)= helper ys (y:acc)
            |otherwise= (reverse acc):helper ys [y]

data Op = Plus Int | Minus Int | Times Int
        deriving (Show, Eq)

simpleCalc :: [Op] -> Int
simpleCalc []=0
simpleCalc xs=helper xs 0
    where
        helper [] count=count
        helper (x:xs) count=case x of
            Plus a -> helper xs (count+a)
            Minus a -> helper xs (count-a)
            Times a -> helper xs (count*a)

mergeSorted :: [Int] -> [Int] -> [Int]
mergeSorted [] ls=ls
mergeSorted ls []=ls
mergeSorted (x:xs) (y:ys)
    |x>y=y:mergeSorted (x:xs) ys
    |otherwise= x:mergeSorted (xs) (y:ys)

replaceIndices :: String -> [Int] -> Char -> String
replaceIndices str indices char= helper str indices 0
    where
        helper "" _ _=""
        helper str [] _=str
        helper (x:xs) (i:is) accind
            |accind==i=char:helper xs is (accind+1)
            |otherwise=x:helper xs (i:is) (accind+1)

dropEveryKth :: [a] -> Int -> [a]
dropEveryKth [] _ =[]
dropEveryKth (x:xs) k=helper (x:xs) k 1
    where
        helper [] k acck=[]
        helper (x:xs) k acck
            |k==acck= helper xs k 1
            |otherwise= x:helper (xs) k (acck+1)

expandList :: [(Int, a)] -> [a]
expandList [] = []
expandList ((0, _):xs) = expandList xs  -- Ha 0 db kell, eldobjuk, jöhet a kövi.
expandList ((n, y):xs) = y : expandList ((n-1, y):xs) -- Kiírjuk y-t, és csökkentjük a darabszámot.

isSubsequence :: (Eq a) => [a] -> [a] -> Bool
isSubsequence [] _=True
isSubsequence _ []=False
isSubsequence (x:xs) (y:ys)
    |x==y=isSubsequence xs ys
    |otherwise=isSubsequence (x:xs) ys

checkParentheses :: String -> Bool
checkParentheses []=True
checkParentheses (')':_)=False
checkParentheses (l:ls)= helper ls 1
    where
        helper [] 0=True
        helper [] _=False
        helper (x:xs) count
            |x=='('=helper xs (count+1)
            |count<0=False
            |otherwise=helper xs (count-1)

data Tree = Leaf Int            -- Levél: csak egy szám
          | Node Int Tree Tree  -- Csomópont: szám, bal ág, jobb ág
          deriving (Show, Eq)

sumOddNodes :: Tree -> Int
sumOddNodes (Leaf n)
    |odd n=n
    |otherwise= 0
sumOddNodes (Node n left right)
    |odd n=n+sumOddNodes left + sumOddNodes right
    | otherwise = 0 + sumOddNodes left + sumOddNodes right

mapMaybeIndex :: (Int -> a -> Maybe b) -> [a] -> [b]
mapMaybeIndex f lista=helper lista 0
    where
        helper [] _=[]
        helper (x:xs) i=case f i x of
            Nothing->helper xs (i+1)
            Just y -> y:helper xs (i+1)


treeMax :: Tree -> Int
treeMax (Leaf n)=n
treeMax (Node n left right)=maximum [n,treeMax left, treeMax right]

mirrorTree :: Tree -> Tree
mirrorTree (Leaf n) = Leaf n  -- Levelet nem kell tükrözni (vagy önmaga)
mirrorTree (Node n left right) = Node n (mirrorTree right) (mirrorTree left)
-- Látod? Megcseréltük őket, DE meg is hívtuk rájuk a tükrözést!

splitOnComma :: String -> [String]
splitOnComma ""=[]
splitOnComma (x:xs)=helper (x:xs) ""
    where
        helper "" acc=[reverse acc]
        helper (x:xs) acc
            |x==','=reverse acc:helper xs ""
            |otherwise=helper xs (x:acc)

collectLeaves :: Tree -> [Int]
collectLeaves (Leaf n)=[n]
collectLeaves (Node n right left)=collectLeaves right++collectLeaves left

safeIndex :: [a] -> Int -> Maybe a
safeIndex [] _=Nothing
safeIndex (x:xs) n=helper (x:xs) n 0
    where
        helper [] n _=Nothing
        helper (x:xs) n c
            |n==c=Just x
            |otherwise= helper xs n (c+1)
