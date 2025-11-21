-- Baseline schema migration for ErgoMind application
-- Creates the initial database structure

CREATE TABLE IF NOT EXISTS alertas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    alert_type VARCHAR(50) NOT NULL,
    message VARCHAR(500) NOT NULL,
    alert_level DOUBLE NOT NULL DEFAULT 0,
    date_time TIMESTAMP
);

-- Create index on alert_type for better query performance
CREATE INDEX IF NOT EXISTS idx_alertas_alert_type ON alertas(alert_type);

-- Create index on date_time for time-based queries
CREATE INDEX IF NOT EXISTS idx_alertas_date_time ON alertas(date_time);

