FROM maven:3.9.12-eclipse-temurin-11-alpine

FROM maven:3.9.12-eclipse-temurin-17

USER root

# Install OS dependencies
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    gnupg \
    unzip \
    ca-certificates \
    fonts-liberation \
    libnss3 \
    libxss1 \
    libasound2 \
    libatk-bridge2.0-0 \
    libgtk-3-0 \
    libgbm1 \
    libdrm2 \
    libxkbcommon0 \
    libxdamage1 \
    libxrandr2 \
    libxshmfence1 \
    xdg-utils \
    --no-install-recommends \
    && rm -rf /var/lib/apt/lists/*

RUN curl -fsSL https://dl.google.com/linux/linux_signing_key.pub \
    | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg

RUN echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] \
    http://dl.google.com/linux/chrome/deb/ stable main" \
    > /etc/apt/sources.list.d/google-chrome.list

RUN apt-get update && apt-get install -y \
    google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

RUN CHROME_VERSION=$(google-chrome --version | awk '{print $3}' | cut -d'.' -f1) && \
    DRIVER_VERSION=$(curl -s https://chromedriver.storage.googleapis.com/LATEST_RELEASE_${CHROME_VERSION}) && \
    wget -q "https://chromedriver.storage.googleapis.com/${DRIVER_VERSION}/chromedriver_linux64.zip" && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver && \
    rm chromedriver_linux64.zip

WORKDIR /src

# Cache dependencies
COPY pom.xml .
RUN mvn -q -B dependency:resolve

# Copy project
COPY src ./src
