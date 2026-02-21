module Hf8 where


data Base = A | T | G | C
  deriving (Eq, Show)


isComplement :: [Base] -> [Base] -> Bool
isComplement xs ys = map pair xs == ys
  where
    pair A = T
    pair T = A
    pair C = G
    pair G = C


data Transaction
  = Transfer Int Int        
  | Purchase Int            
  | Receive Int Int String  
  deriving (Show)


netGain :: [Transaction] -> Int
netGain = foldr add 0
  where
    add (Purchase n) acc     = acc - n
    add (Transfer n _) acc   = acc - n
    add (Receive n _ _) acc  = acc + n

wasNegative :: [Transaction] -> Bool
wasNegative ts = go ts 0
  where
    go [] _ = False
    go (x:xs) bal =
      let bal' = bal + delta x
      in (bal' < 0) || go xs bal'

    delta (Purchase n)    = -n
    delta (Transfer n _)  = -n
    delta (Receive n _ _) = n