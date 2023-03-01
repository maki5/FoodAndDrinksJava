CREATE TABLE "drinks" (
    "id" int NOT NULL PRIMARY KEY,
    "name" varchar(20),
    "price" float
);

CREATE TABLE IF NOT EXISTS "food" (
    "id" int NOT NULL PRIMARY KEY,
    "name" varchar(20),
    "price" float
);

CREATE TABLE IF NOT EXISTS "users" (
    "id" int NOT NULL PRIMARY KEY,
    "email" varchar(50),
    "password" varchar(120),
    "role" int
);

CREATE TABLE IF NOT EXISTS "order_food" (
    "order_id" int NOT NULL,
    "food_id" int NOT NULL
);

CREATE TABLE IF NOT EXISTS "orders" (
    "id" int NOT NULL PRIMARY KEY,
    "buyerName" varchar(120),
    "address" varchar(120),
    "price" float
);
