DROP TABLE IF EXISTS forecast_weather;
DROP TABLE IF EXISTS weather_broker;

CREATE TABLE IF NOT EXISTS forecast_weather (

  id                INTEGER PRIMARY KEY AUTO_INCREMENT,
  version           INTEGER,
  date              DATE,
  day               VARCHAR(255),
  high              VARCHAR(255),
  low               VARCHAR(255),
  text              VARCHAR(255),
  weather_broker_id INTEGER
);

CREATE TABLE IF NOT EXISTS weather_broker (
  id      INTEGER PRIMARY KEY AUTO_INCREMENT,
  version INTEGER,
  date_request VARCHAR(255),
  city    VARCHAR(255)
);



ALTER TABLE forecast_weather
  ADD FOREIGN KEY (weather_broker_id) REFERENCES weather_broker(id);