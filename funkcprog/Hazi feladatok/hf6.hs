module HF6 where

toBin :: Integer -> [Int]
toBin n
    | n == 0    = []
    | n >0 = toBinH n
       where
    toBinH 0 = []
    toBinH k = (fromIntegral (k `mod` 2)) : toBinH (k `div` 2)

remdup :: Eq a => [a] -> [a]
remdup [] = []
remdup [x] = [x]
remdup (x:y:xs)
    | x == y    = remdup (y:xs)
    | otherwise = x : remdup (y:xs)

deleteEveryThird :: [a] -> [a]
deleteEveryThird [] = []
deleteEveryThird [x] = [x]
deleteEveryThird [x,y] = [x,y]
deleteEveryThird (x:y:z:xs) = x:y:deleteEveryThird xs

alternate :: [a] -> [a] -> [a]
alternate [] [] = []
alternate [x] _ = [x]
alternate (x:y:xs) (a:b:ys) = x : b : alternate xs ys
