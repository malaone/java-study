
--610. Triangle Judgement
SELECT 
    x,
    y,
    z,
    CASE
        WHEN x + y > z AND x + z > y AND y + z > x THEN 'Yes'
        ELSE 'No'
    END AS 'triangle'
FROM
    triangle
;

--big countries
SELECT
    name, population, area
FROM
    world
WHERE
    area > 3000000 OR population > 25000000
;

SELECT
    name, population, area
FROM
    world
WHERE
    area > 3000000

UNION

SELECT
    name, population, area
FROM
    world
WHERE
    population > 25000000
;

--584. Find Customer Referee
SELECT name FROM customer WHERE referee_id != 2 OR referee_id IS NULL;

--620. Not Boring Movies
select *
from cinema
where mod(id, 2) = 1 and description != 'boring'
order by rating DESC;
