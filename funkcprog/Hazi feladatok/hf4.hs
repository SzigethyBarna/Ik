module HF4 where
    
grading :: Integer -> Integer
grading x
    | x>89 = 5
    | x>79 = 4
    | x>69 = 3
    | x>59 = 2
    | otherwise = 1

sumSquaresFromTo :: Integer -> Integer -> Integer
sumSquaresFromTo n m
  | n > m     = 0
  | otherwise = n*n + sumSquaresFromTo (n + 1) m