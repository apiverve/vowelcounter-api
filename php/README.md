# Vowel Counter API - PHP Package

Vowel Counter analyzes text to count vowels and consonants, providing detailed breakdowns and percentages for linguistic analysis.

## Installation

Install via Composer:

```bash
composer require apiverve/vowelcounter
```

## Getting Started

Get your API key at [APIVerve](https://apiverve.com)

### Basic Usage

```php
<?php

require_once 'vendor/autoload.php';

use APIVerve\Vowelcounter\Client;

// Initialize the client
$client = new Client('YOUR_API_KEY');

// Make a request
$response = $client->execute(['text' => 'The quick brown fox jumps over the lazy dog. This pangram contains every letter of the alphabet at least once.']);

// Print the response
print_r($response);
```


### Error Handling

```php
use APIVerve\Vowelcounter\Client;
use APIVerve\Vowelcounter\Exceptions\APIException;
use APIVerve\Vowelcounter\Exceptions\ValidationException;

try {
    $response = $client->execute(['text' => 'The quick brown fox jumps over the lazy dog. This pangram contains every letter of the alphabet at least once.']);
    print_r($response['data']);
} catch (ValidationException $e) {
    echo "Validation error: " . implode(', ', $e->getErrors());
} catch (APIException $e) {
    echo "API error: " . $e->getMessage();
    echo "Status code: " . $e->getStatusCode();
}
```

### Debug Mode

```php
// Enable debug logging
$client = new Client(
    apiKey: 'YOUR_API_KEY',
    debug: true
);
```

## Example Response

```json
{
  "status": "ok",
  "error": null,
  "data": {
    "vowels": 31,
    "consonants": 58,
    "totalLetters": 89,
    "vowelPercentage": 34.83,
    "consonantPercentage": 65.17,
    "vowelBreakdown": {
      "a": 8,
      "e": 11,
      "i": 3,
      "o": 7,
      "u": 2
    },
    "textLength": 110
  }
}
```

## Requirements

- PHP 7.4 or higher
- Guzzle HTTP client

## Documentation

For more information, visit the [API Documentation](https://docs.apiverve.com/ref/vowelcounter?utm_source=packagist&utm_medium=referral&utm_content=readme).

## Support

- Website: [https://apiverve.com/marketplace/vowelcounter?utm_source=php&utm_medium=referral&utm_content=readme](https://apiverve.com/marketplace/vowelcounter?utm_source=php&utm_medium=referral&utm_content=readme)
- Email: hello@apiverve.com

## License

This package is available under the [MIT License](LICENSE).
