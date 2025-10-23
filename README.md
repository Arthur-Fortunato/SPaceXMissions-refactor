## 💀 DESAFIO DE REFACTORING – AVALIADOR DE MISSÃO (JAVA) 💀

### Regras de Pontuação

1️⃣ Toda missão começa com **100 pontos base**.

2️⃣ Se a missão **teve sucesso** → **+50 pontos**.

3️⃣ Se houve **falha crítica** → **-30 pontos**.

4️⃣ Se o astronauta **é veterano** → **+20 pontos**.

5️⃣ O **destino** influencia:
- `"marte"`  → **+10 pontos**
- `"europa"` → **-5 pontos**
- qualquer outro → **0 ponto**

---
### Regras de Geração de Código da Missão

O código da missão deve seguir o seguinte padrão:
<Inicial da Agência>-<3 primeiras letras do Destino>-<Ano>[-X se for secreta]
**Exemplo:**  
Agência = "NASA"  
Destino = "Marte"  
Ano = 2077  
Secreta = true

Resultado esperado:  
`N-MAR-2077-X`

---

**Detalhes:**
- Pega a **primeira letra da agência**, em maiúsculo.
- Pega as **três primeiras letras do destino**, também em maiúsculo.
- Adiciona o **ano da missão**.
- Se a missão for **secreta**, adiciona o sufixo `-X`.
- O formato final deve ser **idêntico para todas as missões** — útil para auditorias e logs da Agência Galáctica.