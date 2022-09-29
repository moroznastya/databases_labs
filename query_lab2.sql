USE Labor_SQL;

-- 1. БД «Комп. фірма». Знайти номер моделі, об’єм пам’яті та розміри
-- екранів ноутбуків, ціна яких перевищує 1000 дол. Вивести: model, ram,
-- screen, price. Вихідні дані впорядкувати за зростанням за стовпцем
-- ram та за спаданням за стовпцем price.
SELECT model, ram, screen, ROUND(price) AS price FROM laptop WHERE price > 1000 ORDER BY ram, price DESC;

-- 2. БД «Кораблі». Знайти всі кораблі, імена класів яких закінчуються
-- літерою 'o', але не на 'go'.
SELECT * FROM ships WHERE class REGEXP "o$" AND class NOT REGEXP "go$";

-- 3. БД «Комп. фірма». Вкажіть виробника для тих ноутбуків, що мають
-- жорсткий диск об’ємом не менше 10 Гбайт. Вивести: maker, type,
-- speed, hd.
SELECT product.maker, product.type, pc.speed, pc.hd FROM product, pc WHERE pc.hd >= 10;

-- 4. БД «Комп. фірма». Знайдіть виробників, що випускають ПК, але не
-- ноутбуки (використати ключове слово ALL). Вивести maker.
SELECT DISTINCT maker FROM product WHERE type = "PC" AND maker != ALL (SELECT maker FROM product WHERE type = "Laptop");

-- 5. БД «Комп. фірма». Знайдіть виробників, які б випускали одночасно
-- ПК та ноутбуки зі швидкістю 750 МГц та вище. Виведіть: maker. 
SELECT DISTINCT maker FROM product, pc WHERE type = "PC" AND speed >= 750 AND maker IN (SELECT maker FROM product, laptop WHERE type = "Laptop" AND speed >= 750); 

-- 6. БД «Фірма прий. вторсировини». З таблиці Income виведіть дати в
-- такому форматі: рік.число_місяця.день, наприклад, 2001.02.15 (без
-- формату часу).
SELECT DATE(date) AS date FROM income; 

-- 7. БД «Комп. фірма». Знайти тих виробників ПК, усі моделі ПК яких є
-- в наявності в таблиці PC (використовувати засоби групової
-- статистики). Вивести maker.
SELECT maker FROM product WHERE model IN (SELECT model FROM pc) GROUP BY maker;

-- 8. БД «Комп. фірма». Для кожного виробника знайдіть середній
-- розмір екрану для ноутбуків, що ним випускається. Вивести: maker,
-- середній розмір екрану. (Підказка: використовувати підзапити в
-- якості обчислювальних стовпців)
SELECT maker, ROUND(AVG(screen)) AS "average screen" FROM (SELECT maker, model FROM product WHERE type = "Laptop") AS laptop_makers, laptop
WHERE laptop.model = laptop_makers.model GROUP BY maker;

-- 9. БД «Фірма прий. вторсировини». Приймаючи, що прихід та розхід
-- грошей на кожному пункті прийому фіксується не частіше одного
-- разу на день (лише таблиці Income_o та Outcome_o), написати запит із
-- такими вихідними даними: point (пункт), date (дата), inc (прихід), out
-- (розхід). (Підказка: використовувати зовнішнє з’єднання та оператор
-- CASE)
SELECT income_o.point, DATE(income_o.date) AS date, ROUND(income_o.inc) AS inc,
ROUND(CASE
	WHEN outcome_o.out IS NOT NULL THEN outcome_o.out
    ELSE 0
END) AS "out"
FROM income_o LEFT JOIN outcome_o ON income_o.point = outcome_o.point AND income_o.date = outcome_o.date
UNION
SELECT outcome_o.point, DATE(outcome_o.date) AS date,
ROUND(CASE
	WHEN income_o.inc IS NOT NULL THEN income_o.inc
    ELSE 0
END) AS inc,
ROUND(outcome_o.out) AS "out"
FROM income_o RIGHT JOIN outcome_o ON income_o.point = outcome_o.point AND income_o.date = outcome_o.date ORDER BY date;

-- 10. БД «Комп. фірма». Знайдіть середню ціну ПК та ноутбуків, що
-- випущені виробником 'A'. Вивести: одне число для загальної середньої
-- ціни. (Підказка: використовувати оператор UNION)
SELECT ROUND(AVG(price)) AS "average price" FROM ((SELECT model, price FROM laptop) UNION ALL (SELECT model, price FROM pc)) AS prices
WHERE model IN (SELECT model FROM product WHERE maker = "A");