# circuitBreakerDemo

isServiceAvailable() method checks the current state of the circuit breaker (CLOSED, OPEN, or PARTIALLY_OPEN) and determines whether or not to allow service requests
to proceed.
If the circuit breaker is OPEN, it checks if the cooldown period has passed, and if so, changes the state to PARTIALLY_OPEN. 
If the circuit breaker is PARTIALLY_OPEN, it checks if the failure count has exceeded the threshold and if so, changes the state to OPEN and resets the failure count. 
If the circuit breaker is closed it checks if the failure count has exceeded the threshold and if so, changes the state to OPEN.
