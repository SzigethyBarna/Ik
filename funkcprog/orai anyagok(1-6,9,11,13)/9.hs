module Lab09 where
import data.Maybe
import data.Char

data MaybeInt = NotInt | JustInt Int deriving (Show)

safeIntDiv :: Int->Int->MaybeInt
safeIntDiv n 0 = NotInt
safeIntDiv n m =JustInt(n `div` m)

data MaybeInt = NotInt | JustInt Int deriving (Show)

safeDiv :: Integral a => a-> a -> Maybe a
safeDiv n 0 = Nothing
safeDiv n m = Just(n `div` m)

safeHead :: [a] -> Maybe a
safeHead (x:xs) = Just x
safeHead []= Nothing

safeTail :: [a] -> Maybe [a]
safeTail (x:xs) = Just xs
safeTail []= Nothing

maybeAdd :: Num a=> Maybe a ->Maybe a ->Maybe a
maybeAdd (Just x) Nothing = Just x
maybeAdd Nothing (Just x) = Just x
maybeAdd Nothing Nothing = Nothing
maybeAdd (Just x) (Just y)= Just (x+y)

elemIndex :: Eq a => a -> [a] -> Maybe Int
elemIndex _ [] = Nothing
elemIndex y (x:xs) = elemH y x:xs 0
        where
            elemH y [] n = Nothing
            elemH y (x:xs) n
                | y==x=n
                |otherwise= elemH y xs (n+1)

maybeSum :: Num a => [Maybe a] -> a
maybeSum [] =0
maybeSum (Just x:xs)=x + maybeSum xs
maybeSum (Nothing:xs)=maybeSum xs

readInt :: [Char] -> Maybe Int
readInt xs = rd xs 0 where
    rd [] acc =just acc
    rd (x:xs) acc
        |isDigit x = rd xs (acc*10 + digitToInt x)
        |otherwise = Nothing
myReverse :: [a] -> [a]
myReverse (x:xs) =myRevH (x:xs) []
    where
        myRevH [] acc = acc
        myRevH (x:xs) acc = myRevH xs (x:acc)