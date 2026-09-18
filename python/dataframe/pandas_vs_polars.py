"""
A Pandas az R nyelvből jött a Pythonba, míg az újabb Polars a Rust nyelvből insiprálódik.
Mindkettő tabuláris adatok gyors kezelését kívánja elősegíteni.

Gondoljuk át a hasonlóságokat és a különbségeket az SQL-utasításokkal,
 az Unix alapvető segédprogramjaival és az AWK-val,
 valamint a tiszta Python-megvalósítással összefüggésben!

Mikor melyiket érdemes használni?
"""

import pyarrow

import polars as pl

# 1. DataFrame létrehozása

pldf = pl.DataFrame({
    "name": ["Alice", "Bob", "Chris"],
    "age": [30, 22, 25],
    "city": ["London", "Paris", "Berlin"]
})

print(pldf)

import pandas as pd

# Ugyanaz, mint a pandas esetében
pddf = pd.DataFrame({
    "name": ["Alice", "Bob", "Chris"],
    "age": [30, 22, 25],
    "city": ["London", "Paris", "Berlin"]
})

print(pldf)

# ---------------------------------------------------

# 2. Egy oszlop kiválasztása (name)

df_name = pldf.select("name")  # Explicitebb és objektum orientáltabb
print(df_name)

df_name = pddf["name"]
print(df_name)

# ---------------------------------------------------

# 3. A age > 23 sorok kiszűrése

df_filtered = pldf.filter(pl.col("age") > 23)  # Pythonikusabb. Általánosan definiáljuk az oszlop nevet!
print(df_filtered)

df_filtered = pddf[pddf["age"] > 23]
print(df_filtered)

# ---------------------------------------------------

# 4. Egy új számított oszlop hozzáadása: age_in_5_years = age + 5

pldf_new = pldf.with_columns((pl.col("age") + 5).alias("age_in_5_years"))  # Új DataFrame (Polarsban immutable)
print(pldf_new)

pddf["age_in_5_years"] = pddf["age"] + 5  # Megváltoztatja az eredeti DataFrame-et (Pandasban mutable)
print(pddf)

# ---------------------------------------------------

# 5. Oszlop átnevezése (city -> location)

pldf_renamed = pldf.rename({"city": "location"})  # Csak szintaxisbeli különbség
print(pldf_renamed)

pddf_renamed = pddf.rename(columns={"city": "location"})
print(pddf_renamed)

# ---------------------------------------------------

# 6. Csoportosítás és aggregálás

pldf2 = pl.DataFrame({
    "product": ["apple", "banana", "apple"],
    "price": [1.2, 0.5, 1.1],
    "quantity": [10, 20, 15]
})

pldf_grouped = pldf2.group_by("product").agg([  # Függvények lánca -> optimalizálható, segíti a statikus elemzést
    pl.sum("quantity").alias("total_quantity"),
    pl.mean("price").alias("avg_price")
])

print(pldf_grouped)

pddf2 = pd.DataFrame({
    "product": ["apple", "banana", "apple"],
    "price": [1.2, 0.5, 1.1],
    "quantity": [10, 20, 15]
})

pddf_grouped = pddf2.groupby("product").agg(  # Sok implicit vagy karakterlánc-értékű parancs, amelyet könnyű elgépelni
    total_quantity=("quantity", "sum"),
    avg_price=("price", "mean")
).reset_index()

print(pddf_grouped)

# ---------------------------------------------------

# 7. Rendezés

pldf_sorted = pldf.sort("age", descending=True)  # Figyeljük meg a szintaktikai különbséget
                                                 #  vs. python sort(reverse=True|False) -> ez kevésbé explicit
print(pldf_sorted)

pddf_sorted = pddf.sort_values("age", ascending=False)
print(pddf_sorted)

# ---------------------------------------------------

# 8. Több oszlop kijelölése kifejezések segítségével

pldf_expr = pldf.select([
    (pl.col("age") ** 2).alias("age_squared"),  # Az SQL-utasításokat utánozva
    pl.col("name").str.len_chars().alias("name_length")  # Explicit karakterbeli hossz vs bájtbeli hossz
])
print(pldf_expr)

pddf_expr = pd.DataFrame({
    "age_squared": pddf["age"] ** 2,
    "name_length": pddf["name"].str.len()  # Inkább NumPy stílusú
})
print(pddf_expr)

# ---------------------------------------------------

# 9. Oszlopok feltételes létrehozása

pldf_cond = pldf.with_columns(  # Új DataFrame
    pl.when(pl.col("age") >= 18).then(True).otherwise(False).alias("is_adult")  # Explicit feltételes elágazás,
                                                                                # láncolható
)
print(pldf_cond)

pddf["is_adult"] = pddf["age"] >= 18  # Implicit, megváltoztatja a DataFrame-et
print(pddf)

# ---------------------------------------------------

# 10. CSV-fájlok írása/olvasása (v.ö. a Python beépített csv moduljával)

# Write CSV
pldf.write_csv("data_polars.csv")  # Érdemes megnézni az opcionális paramétereket

# Read CSV
pldf_loaded = pl.read_csv("data_polars.csv")  # Classmethod-ok, mint alternatív konstruktorok
print(pldf_loaded)

# Write CSV
pddf.to_csv("data_pandas.csv", index=False)

# Read CSV
pddf_loaded = pd.read_csv("data_pandas.csv")
print(pddf_loaded)

# ---------------------------------------------------

# 11. CSV betöltése és a hiányzó adatok felkutatása

pldf = pl.read_csv("people.csv")  # Tegyük fel, hogy az alábbi oszlopok léteznek: name, age, city

# Hiányzó értékek száma oszloponként
plmissing_count = pldf.null_count()
print(plmissing_count)

# A hiányzó értékek aránya
plmissing_percentage = pldf.null_count().with_columns((pl.all() / pldf.height * 100).round(2))
print(plmissing_percentage)

# Az „age” null értékű sorok törlése
pldf_cleaned = pldf.filter(pl.col("age").is_not_null())
print(pldf_cleaned)

pddf = pd.read_csv("people.csv")

# Hiányzó értékek száma oszloponként
pdmissing_count = pddf.isnull().sum()  # Két lépésben vs. Polars
print(pdmissing_count)

# A hiányzó értékek aránya
pdmissing_percentage = (pddf.isnull().mean() * 100).round(2)
print(pdmissing_percentage)

# Az „age” null értékű sorok törlése
pddf_cleaned = pddf.dropna(subset=["age"])
print(pddf_cleaned)

# ---------------------------------------------------

# 12. Dátum oszlop konvertálása (v.ö a Python beépített datetime modulja)

pldf = pl.DataFrame({
    "date": ["2024-05-10", "2023-12-31"]
})

pldf_parsed = pldf.with_columns([
    pl.col("date").str.strptime(pl.Date, "%Y-%m-%d").alias("date_parsed")
])

pldf_parsed = pldf_parsed.with_columns([
    pl.col("date_parsed").dt.year().alias("year"),
    pl.col("date_parsed").dt.month().alias("month"),
    pl.col("date_parsed").dt.day().alias("day")
])

print(pldf_parsed)

pddf = pd.DataFrame({
    "date": ["2024-05-10", "2023-12-31"]
})

pddf["date_parsed"] = pd.to_datetime(pddf["date"], format="%Y-%m-%d")
pddf["year"] = pddf["date_parsed"].dt.year
pddf["month"] = pddf["date_parsed"].dt.month
pddf["day"] = pddf["date_parsed"].dt.day

print(pddf)

# ---------------------------------------------------

# 13. Csoportos idősor-újramintavételezés

pldf = pl.DataFrame({
    "timestamp": ["2024-01-01 15:00", "2024-01-01 15:01", "2024-01-01 15:04"],
    "user_id": [1, 1, 2]
}).with_columns(pl.col("timestamp").str.strptime(pl.Datetime, "%Y-%m-%d %H:%M"))

pldf_resampled = pldf.group_by_dynamic(
    index_column="timestamp",
    every="1m",  # Itt a formátum string literálként van megadva. Hibaérzékeny!
    group_by="user_id",
    closed="left",
    include_boundaries=True
).agg(pl.count("timestamp").alias("event_count"))

print(pldf_resampled)

pddf = pd.DataFrame({
    "timestamp": ["2024-01-01 15:00", "2024-01-01 15:01", "2024-01-01 15:04"],
    "user_id": [1, 1, 2]
})

pddf["timestamp"] = pd.to_datetime(pddf["timestamp"])

pddf_resampled = (
    pddf.set_index("timestamp")
    .groupby("user_id")[[]]  # Explicit módon kizárjuk az egyéb adatoszlopokat
    .resample("1min")  # 1 perces időablak
    .size()
    .reset_index(name="event_count")
)

print(pddf_resampled)

# ---------------------------------------------------

# 14. Statisztikák kiszámítása

pldf = pl.DataFrame({
    "price": [1.2, 0.5, 1.1],
    "quantity": [10, 20, 15]
})

plstats = pldf.select([
    pl.mean("price").alias("mean_price"),
    pl.median("price").alias("median_price"),
    pl.std("price").alias("std_price"),
    pl.quantile("price", 0.1).alias("q10_price"),
    pl.quantile("price", 0.5).alias("q50_price"),
    pl.quantile("price", 0.9).alias("q90_price"),
    pl.corr("price", "quantity").alias("corr_price_quantity")
])

print(plstats)

pddf = pd.DataFrame({
    "price": [1.2, 0.5, 1.1],
    "quantity": [10, 20, 15]
})

pdstats = pd.DataFrame({
    "mean_price": [pddf["price"].mean()],
    "median_price": [pddf["price"].median()],
    "std_price": [pddf["price"].std()],
    "q10_price": [pddf["price"].quantile(0.1)],
    "q50_price": [pddf["price"].quantile(0.5)],
    "q90_price": [pddf["price"].quantile(0.9)],
    "corr_price_quantity": [pddf["price"].corr(pddf["quantity"])]
})

print(pdstats)

# ---------------------------------------------------

# 15. Két DataFrame összekapcsolása

plusers = pl.DataFrame({
    "user_id": [1, 2],
    "name": ["Alice", "Bob"]
})

plorders = pl.DataFrame({
    "order_id": [100, 101, 102],
    "user_id": [1, 1, 2],
    "amount": [50, 70, 30]
})

pldf_joined = plusers.join(plorders, on="user_id", how="inner")  # Hasonlít az SQL-utasításokra
pldf_total = pldf_joined.group_by("name").agg(pl.sum("amount").alias("total_spent"))
print(pldf_total)

pdusers = pd.DataFrame({
    "user_id": [1, 2],
    "name": ["Alice", "Bob"]
})

pdorders = pd.DataFrame({
    "order_id": [100, 101, 102],
    "user_id": [1, 1, 2],
    "amount": [50, 70, 30]
})

pddf_joined = pd.merge(pdusers, pdorders, on="user_id", how="inner")
pddf_total = pddf_joined.groupby("name")["amount"].sum().reset_index(name="total_spent")
print(pddf_total)

# ---------------------------------------------------

# 16. Pivot tábla készítés

pldf = pl.DataFrame({
    "day": ["Mon", "Mon", "Tue", "Tue"],
    "product": ["A", "B", "A", "B"],
    "sales": [10, 20, 15, 25]
})

pldf_pivot = pldf.pivot(values="sales", index="product", on="day")
print(pldf_pivot)

pddf = pd.DataFrame({
    "day": ["Mon", "Mon", "Tue", "Tue"],
    "product": ["A", "B", "A", "B"],
    "sales": [10, 20, 15, 25]
})

pddf_pivot = pddf.pivot(index="product", columns="day", values="sales").reset_index()
print(pddf_pivot)

# ---------------------------------------------------

# 17. Lusta módú beolvasás / scan_csv()

pldf_lazy = pl.scan_csv("large_data.csv")  # Lusta CSV beolvasás

plresult = (
    pldf_lazy.filter(pl.col("price") > 10)
    .group_by("category")
    .agg(pl.sum("price").alias("total_price"))
    .collect()  # Ez indítja el a tényleges számítást (v.ö az SQL query plan)
)

print(plresult)

pddf = pd.read_csv("large_data.csv")

pdresult = pddf[pddf["price"] > 10].groupby("category")["price"].sum().reset_index(name="total_price")
print(pdresult)

# ---------------------------------------------------

# 18. Parquet írása és olvasása

pldf.write_parquet("data_polars.parquet")
pldf_parquet = pl.read_parquet("data_polars.parquet")
print(pldf_parquet)

pddf.to_parquet("data_pandas.parquet", index=False)
pddf_parquet = pd.read_parquet("data_pandas.parquet")
print(pddf_parquet)

# ---------------------------------------------------

# 20. Több CSV-fájl egyesítése

from pathlib import Path

plfiles = Path("data_folder/").glob("data_*.csv")
pldfs = [pl.read_csv(f) for f in plfiles]
pldf_combined = pl.concat(pldfs)
pldf_cleaned = pldf_combined.drop_nulls()
pldf_cleaned.write_parquet("combined_polars.parquet")

pdfiles = Path("data_folder/").glob("data_*.csv")
pddfs = [pd.read_csv(f) for f in pdfiles]
pddf_combined = pd.concat(pddfs, ignore_index=True)
pddf_cleaned = pddf_combined.dropna()
pddf_cleaned.to_parquet("combined_pandas.parquet", index=False)
