module N15 where


type Apple = (Bool, Int)
type Tree = [Apple]
type Garden = [Tree] --[[(Bool,int)]]


countTree :: Tree->Int
countTree []=0
countTree ((ripe,height):xs)
    |ripe && height <=3 =1+ countTree xs
    |otherwise = countTree xs

ryuksApples :: Garden -> Int
ryuksApples [] =0
ryuksApples (t:ts) = countTree t + ryuksApples ts


points :: Integral a => [(String, a, a)] -> [(String, a)]
points [] =[]
points ((name, time, hiba):xs)
    |hiba ==100 = points xs
    | pont <= 0          = points xs
    | otherwise          = (name, pont) : points xs
        where
            pont = 100 - (time `div` 2) - hiba

doesContain :: String -> String -> Bool
doesContain "" _ =True
doesContain _ "" =False
doesContain (x:xs) (y:ys)
    |x==y= doesContain xs ys
    |otherwise= doesContain (x:xs) ys

barbie :: [String] -> String
barbie xs = helper 1 xs
    where
        helper _ [] ="farmer"
        helper i (x:xs) 
            |x=="rozsaszin" =x
            |even i && x/= "fekete" =x
            |otherwise= helper (i+1) xs

firstValid :: [a -> Bool] -> a -> Maybe Int
firstValid [] _ = Nothing
firstValid xs n = helper xs n 0
    where
        helper [] _ i = Nothing
        helper (x:xs) n i
            |x n==True=Just i
            |otherwise= helper xs n (i+1)

combineListsIf :: (a -> b -> Bool) -> (a -> b -> c) -> [a] -> [b] -> [c]
combineListsIf _ _ [] _ = []
combineListsIf _ _ _ [] = []
combineListsIf p f (x:xs) (y:ys)
    |p x y == True = (f x y) : combineListsIf p f xs ys
    |otherwise= combineListsIf p f xs ys

data Line 
    = Tram Integer [String]
    | Bus  Integer [String]
    deriving (Eq, Show)



whichBusStop :: String -> [Line] -> [Integer]
whichBusStop _ []=[]
whichBusStop stop (l:ls)=
    case l of 
        Tram _ _ -> whichBusStop stop ls
        Bus n stops
            |stop `elem` stops -> n: whichBusStop stop ls
            |otherwise -> whichBusStop stop ls

isReservable :: Int -> String -> Bool
isReservable n ls = helper ls 0
    where
        helper [] c = c >= n
        helper (x:xs) c 
            |n<=c=True
            |x=='x'= helper xs (c+1)
            |otherwise =helper xs 0