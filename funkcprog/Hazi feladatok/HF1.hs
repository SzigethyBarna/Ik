module HF where

--Első feladat

xor3 ::  Bool -> Bool -> Bool -> Bool
xor3 True True True = True
xor3 False False True = True
xor3 False True False=True
xor3 True False False= True
xor3 _ _ _ = False

--Második feladat

exactlyOneTrue :: Bool -> Bool -> Bool -> Bool

exactlyOneTrue True False False=True
exactlyOneTrue False True False=True
exactlyOneTrue False False True=True
exactlyOneTrue _ _ _ = False

--Harmadik feladat

deleteIf :: Char -> Bool -> Char

deleteIf a True=' '
deleteIf a False=a

