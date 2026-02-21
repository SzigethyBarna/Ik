module Vizs4 where
import Data.Char

data Hely = Lake Double 
          | Mountain Double 
          | Cave Double
          deriving (Show, Eq)

deepestLake :: [Hely] -> Maybe Double
deepestLake ds = case [d | Lake d <- ds] of
                    [] -> Nothing
                    ds -> Just (maximum ds)

maxOfMaybes :: [Maybe Int] -> Int
maxOfMaybes lista = maximum [x | Just x <- lista]


-- A típus: Listák listáját várja, és listák listáját adja vissza.
-- Az 'a' bármi lehet (Int, Bool, Char, stb.)
duplazMasodik :: [[a]] -> [[a]]
duplazMasodik listak = map listaAlakit listak
  where
    -- Segédfüggvény: egy darab listát alakít át
    listaAlakit :: [a] -> [a]
    listaAlakit (x:y:zs) = x : y : y : zs  -- Ha van legalább 2 elem: x marad, y dupláz, többi marad
    listaAlakit egyeb    = egyeb           -- Ha 0 vagy 1 elemű, nem bántjuk

dropUntil :: ([a] -> Bool) -> [a] -> [a]
dropUntil _ [] = []      -- Ha elfogyott a lista, üreset adunk vissza
dropUntil p xs           -- 'xs' itt a TELJES aktuális lista
  | p xs      = xs       -- Ha a jelenlegi lista megfelel a feltételnek, készen vagyunk (visszaadjuk)
  | otherwise = dropUntil p (tail xs) -- Ha nem, eldobjuk az első elemet (tail) és újrapróbáljuk

-- A fő függvény, ami a listán megy végig
canBeFactorised :: [Int] -> [[Int]]
canBeFactorised szamok = map faktorizalo szamok

-- A segédfüggvény, ami EGY számot bont fel
faktorizalo :: Int -> [Int]
faktorizalo n = osztoKereses n 2
  where
    -- 'k' az aktuális szám, 'd' az aktuális osztó (2-től indul)
    osztoKereses 1 _ = [1]  -- Ha elértünk az 1-ig, vége, betesszük az 1-est
    osztoKereses k d
      | k `mod` d == 0 = d : osztoKereses (k `div` d) d  -- Ha osztható, felírjuk d-t, és osztunk
      | otherwise      = osztoKereses k (d + 1)          -- Ha nem osztható, jöhet a kövi szám

sumEvenJusts :: [Maybe Int] -> Int
sumEvenJusts ds=sum [d|Just d<-ds,d `mod` 2==0]

doubleOrDrop :: [Int] -> [Int]
doubleOrDrop []=[]
doubleOrDrop (x:xs)
    |x `mod` 2 /=0 = (2*x):doubleOrDrop xs
    |otherwise= doubleOrDrop xs

elementIsAt :: [a] -> Int -> Bool
elementIsAt [] _=False
elementIsAt _ n
    |n<0=False
elementIsAt (x:xs) 0=True
elementIsAt (x:xs) n=elementIsAt xs (n-1)

swapAll :: [(a,b)] -> [(b,a)]
swapAll []=[]
swapAll ((x,y):xs)=(y,x):swapAll xs

safeAdd :: Maybe Int -> Maybe Int -> Maybe Int
safeAdd Nothing _=Nothing
safeAdd _ Nothing =Nothing
safeAdd (Just a) (Just b)=Just (a+b)

countTrue :: [Bool] -> Int
countTrue ls = helper ls 0
    where
        helper [] c=c
        helper (x:xs) c
            |x==True= helper xs (c+1)
            |otherwise= helper xs c

onlyCapitals :: String -> String
onlyCapitals ""=""
onlyCapitals (x:xs)
    |x  `elem` ['A'..'Z']= x:onlyCapitals xs
    |otherwise= onlyCapitals xs

everySecond :: [a] -> [a]
everySecond []=[]
everySecond [x]= [x]
everySecond (x:y:xs)=x:everySecond xs

processGrades :: [(String, Maybe Int)] -> [String]
processGrades lista
    |Nothing `elem` [jegy |(_,jegy)<-lista]=[]
    |otherwise=[nev|(nev,Just jegy)<-lista,jegy>1]

zipWithPadding :: [Int] -> [Int] -> [Int]
zipWithPadding (x:xs) (y:ys)=(x+y):zipWithPadding xs ys
zipWithPadding [] ys=ys
zipWithPadding xs []=xs

longestRising :: [Int] -> Int
longestRising [] = 0
longestRising xs = helper xs 1 1 -- Min. hossz 1, ha nem üres
  where
    helper [] _ m = m
    helper [x] c m = max c m       -- Vége: visszaadjuk a nagyobbat
    helper (x:y:xs) c m
        | y > x     = helper (y:xs) (c+1) m -- Növekszik: c növel, y-t visszük tovább!
        | otherwise = helper (y:xs) 1 (max c m) -- Megszakadt: c reset, max mentése

transformNested :: [[Int]] -> [[Int]]
transformNested [] =[]
transformNested (x:xs)
    |(sum x) `mod` 2==0 =(map (*10) x):transformNested xs
    |otherwise=transformNested xs


findFirstValid :: [Maybe a] -> (a -> Bool) -> Maybe a
findFirstValid [] _=Nothing
findFirstValid (Nothing : xs) p = findFirstValid xs p -- 2. eset: Nothing -> Ugrunk a következőre
findFirstValid (Just x : xs) p                     -- 3. eset: Van érték (Just)
    | p x       = Just x                           -- Ha igaz rá a feltétel -> Megvan!
    | otherwise = findFirstValid xs p              -- Ha nem igaz -> Keresünk tovább

applyToKey :: String -> (Int -> Int) -> [(String, Int)] -> Maybe Int
applyToKey _ _ []=Nothing
applyToKey szoveg f ((x,y):xs)
    |x==szoveg=Just (f y)
    |otherwise= applyToKey szoveg f xs

alternatingSum :: [Int] -> Int
alternatingSum []=0
alternatingSum [x]=x
alternatingSum (x:y:xs) =(x-y)+alternatingSum xs

safeUpdate :: [a] -> Int -> a -> [a]
safeUpdate [] _ _=[]
safeUpdate lista index uj=helper lista index 0
    where
        helper [] _ _= []
        helper(x:xs) index c
            |c==index=(uj:xs)
            |otherwise= x:helper xs index (c+1)

compress :: String -> [(Char, Int)]
compress ""=[]
compress (x:xs) =helper xs x 1
    where
        helper [] elozo counter=[(elozo,counter)]
        helper (x:xs) elozo counter
            |x==elozo=helper xs elozo (counter+1)
            |otherwise=(elozo,counter):helper xs x 1

mergeJusts :: [String] -> [Maybe Int] -> [(String, Int)]
mergeJusts [] _=[]
mergeJusts _ []=[]
mergeJusts (nev:nevek) (jegy:jegyek) = case jegy of
    Just ertek ->(nev,ertek):mergeJusts nevek jegyek
    Nothing -> mergeJusts nevek jegyek
