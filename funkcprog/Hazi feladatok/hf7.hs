module HF7 where 

password :: [Char] -> [Char]
password xs = [ '*' | _ <- xs ]

divisors :: Integer -> [Integer]
divisors n = [ x | x <- [1 .. n-1], n `mod` x == 0 ]

areAmicableNumbers :: Integer -> Integer -> Bool
areAmicableNumbers a b = sum (divisors a) == b && sum (divisors b) == a

filterIncPairs :: Ord a => [(a,a)] -> [(a,a)]
filterIncPairs xs = [ (a,b) | (a,b) <- xs, a < b ]
