module Hf9 where


triangleArea :: (Double, Double, Double) -> Maybe Double
triangleArea (a, b, c)

  | a <= 0 || b <= 0 || c <= 0 = Nothing
  | a + b <= c || a + c <= b || b + c <= a = Nothing
  | a*a + b*b == c*c = Just (a * b / 2)
  | otherwise = Nothing



data Storage
  = HDD String Int Int
  | SSD String Int
  deriving (Show, Eq)


largestSSD :: [Storage] -> Maybe Storage
largestSSD storages =
  let
    onlySSDs = [s | s@(SSD _ _) <- storages]
  in
    case onlySSDs of
      
      [] -> Nothing


      _  -> Just (maxSSD onlySSDs)


maxSSD :: [Storage] -> Storage
maxSSD (s:ss) = foldl biggerSSD s ss
  where
    biggerSSD (SSD name1 cap1) (SSD name2 cap2)
      | cap1 >= cap2 = SSD name1 cap1
      | otherwise    = SSD name2 cap2


hugeHDDs :: [Storage] -> [Storage]
hugeHDDs storages =
  case largestSSD storages of
    Nothing -> []
    Just (SSD _ biggestCap) ->
      [ h | h@(HDD _ _ cap) <- storages, cap > biggestCap ]
