module Elso where

--komment
{-több sor
komment-}
one :: Int
one = 1

inc:: Int -> Int
inc x = x+1

add3 :: Int->Int->Int->Int
add3  x y z=x+y+z

not' :: Bool->Bool
not' True = False
not' _ = True

and' :: Bool->Bool->Bool
and' True True=True
and' _ _=False

(|:|)::Bool->Bool->Bool
(|:|) False False=False
(|:|) _ _=True

(-->) ::Bool->Bool->Bool
True-->False=False
_-->_=True

xor :: Bool->Bool->Bool
xor False True=True
xor True False=True
xor _ _=False

isA :: Char->Bool
--isA 'a'=True
--isA 'A'=True
--isA _=False
isA x=x =='a' || x=='A'

replaceNewLine :: Char->Char
replaceNewLine '\n'=' '
replaceNewLine x=x
