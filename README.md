![Banner](https://github.com/user-attachments/assets/1fee89be-6194-46ab-95b3-54348e33dc12)
# Villa
# Qorder Condomínio

Aplicativo Android para gestão de condomínios desenvolvido em Kotlin.

## 📱 Funcionalidades

- **Notícias do Condomínio**: Visualização de comunicados e avisos importantes
- **Sistema de Denúncias**: Registro e acompanhamento de denúncias pelos moradores
- **Reservas de Espaços**: Agendamento de áreas comuns como salão de festas, quadra e deck

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **Framework**: Android SDK
- **Arquitetura**: Componentes do Android Architecture (Fragments, Navigation)
- **Bibliotecas**:
  - AndroidX
  - Material Design Components
  - Navigation Component

## 📂 Estrutura do Projeto

```
app/
├── src/main/java/com/condominio/
│   ├── model/              # Modelos de dados (Noticia, Denuncia)
│   ├── ui/                 # Interfaces e lógica de apresentação
│   │   ├── home/           # Tela de notícias
│   │   ├── denuncia/       # Tela de denúncias
│   │   └── reservas/       # Tela de reservas
│   └── MainActivity.kt     # Atividade principal
├── src/main/res/           # Recursos (layouts, imagens, strings)
└── build.gradle.kts        # Configurações do Gradle
```

## 🎨 Telas do Aplicativo

### Tela Principal - Notícias
Visualize os comunicados e avisos mais recentes do condomínio.

### Tela de Denúncias
Registre problemas e irregularidades no condomínio de forma anônima.

### Tela de Reservas
Agende espaços comuns como:
- Salão de festas
- Quadra poliesportiva
- Deck

## 📄 Licença

Este projeto é privado.
