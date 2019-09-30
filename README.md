# priceApp
Test app to fetch stock/index prices from Yahoo and display as HTML chart

This test app contains
1. REST service to get index/stock data from Yahoo finance and post to HTML as JSON
2. HTML view to visualise the historical data as line charts


# HTML view
  - Input : Symbol, StartDate and EndDate
  - PriceIndicators to select - Open, Close, High, Low
  - Output : Line Charts for each indicator from JSON data
  - Used Chart.js library for charting in HTML/JS

# PriceApp - backend REST service
  - REST api : /priceService/:symbol/:startDate/:endDate
  - Output : JSON dataset for given date range price indicators for the symbol
  - Used sparkJava microframework for simple REST api functionality
  
  
# How to Run
  1. To start the server run the included pre-build jar package : priceService-0.0.1-SNAPSHOT-jar-with-dependencies.jar
  in windows CMD prompt
    - $> java -cp .\priceService-0.0.1-SNAPSHOT-jar-with-dependencies.jar priceApp.priceApp
      
  2. Launch the priceAp p.html in browser.
  
  3. Input test symbol (e.g. SPY, GOOG etc), date range and price indicators to view the historical price chart.
