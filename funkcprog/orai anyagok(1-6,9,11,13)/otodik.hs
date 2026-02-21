module Otodik where

--http://lambda.inf.elte.hu/Lists_new.xml#list%C3%A1k-mintailleszt%C3%A9se

isEmpty :: [a] -> Bool
isEmpty (x:xs) = False
isEmpty [] = True

isSingleton :: [a] -> Bool
isSingleton (_:[]) = True
isSingleton _ = False

f(True:xs)='f'
--[True] 
--[True, False]

g(1:2:_) ='g' 
--[1,2]

h (x:False:xs) = 'h' 
--[True,False,True]
--[True,False]

i ([]:xs) = 'i'
--[[],[],[]]
--[[]]
--[[],[1,2,3][]]
--listak listaja, amely ures listaval kezdodik

