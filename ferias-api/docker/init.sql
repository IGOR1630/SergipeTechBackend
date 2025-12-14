
CREATE TABLE IF NOT EXISTS servidor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    matricula VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    servidor_id INTEGER REFERENCES servidor(id),
    username VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS periodo_ferias (
    id SERIAL PRIMARY KEY,
    servidor_id INTEGER NOT NULL REFERENCES servidor(id),
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    quantidade_dias INTEGER NOT NULL,
    ano_referencia INTEGER NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDENTE',
    data_solicitacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    observacao TEXT
);

CREATE TABLE IF NOT EXISTS pagamento_ferias (
    id SERIAL PRIMARY KEY,
    periodo_ferias_id INTEGER UNIQUE REFERENCES periodo_ferias(id),
    valor_bruto DECIMAL(10,2) NOT NULL,
    valor_liquido DECIMAL(10,2) NOT NULL,
    data_pagamento DATE NOT NULL,
    tipo_pagamento VARCHAR(50) NOT NULL
);


INSERT INTO servidor (nome, cpf, matricula, email) VALUES 
('João da Silva', '12345678901', 'SERV001', 'joao.silva@gov.br'),
('Maria Santos', '98765432109', 'SERV002', 'maria.santos@gov.br');

INSERT INTO usuario (servidor_id, username, senha) VALUES 
(1, 'joao.silva', '123456'),
(2, 'maria.santos', '123456');

INSERT INTO periodo_ferias (servidor_id, data_inicio, data_fim, quantidade_dias, ano_referencia, status, observacao) VALUES 
(1, '2024-01-15', '2024-01-30', 15, 2023, 'APROVADO', 'Férias de janeiro'),
(1, '2024-07-01', '2024-07-15', 15, 2023, 'APROVADO', 'Férias de julho'),
(1, '2025-02-01', '2025-02-28', 28, 2024, 'PENDENTE', 'Aguardando aprovação'),
(2, '2024-12-20', '2025-01-05', 16, 2024, 'APROVADO', 'Férias de fim de ano');

INSERT INTO pagamento_ferias (periodo_ferias_id, valor_bruto, valor_liquido, data_pagamento, tipo_pagamento) VALUES 
(1, 5500.00, 4800.00, '2024-01-10', 'PAGAMENTO_NORMAL'),
(2, 5500.00, 4800.00, '2024-06-25', 'PAGAMENTO_NORMAL'),
(4, 6200.00, 5400.00, '2024-12-15', 'TERCO_CONSTITUCIONAL');

--