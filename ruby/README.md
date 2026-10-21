# Vowel Counter API - Ruby Gem

Vowel Counter analyzes text to count vowels and consonants, providing detailed breakdowns and percentages for linguistic analysis.

## Installation

Add this line to your application's Gemfile:

```ruby
gem 'apiverve_vowelcounter'
```

And then execute:

```bash
$ bundle install
```

Or install it yourself as:

```bash
$ gem install apiverve_vowelcounter
```

## Getting Started

Get your API key at [APIVerve](https://apiverve.com)

### Basic Usage

```ruby
require 'apiverve_vowelcounter'

# Initialize the client
client = APIVerve::Vowelcounter::Client.new(api_key: "YOUR_API_KEY")

# Make a request
response = client.execute({ text: "The quick brown fox jumps over the lazy dog. This pangram contains every letter of the alphabet at least once." })

# Print the response
puts response
```


### Error Handling

```ruby
begin
  response = client.execute({ text: "The quick brown fox jumps over the lazy dog. This pangram contains every letter of the alphabet at least once." })
  puts response["data"]
rescue APIVerve::Vowelcounter::ValidationError => e
  puts "Validation error: #{e.errors.join(', ')}"
rescue APIVerve::Vowelcounter::APIError => e
  puts "API error: #{e.message}"
  puts "Status code: #{e.status_code}"
end
```

### Debug Mode

```ruby
# Enable debug logging
client = APIVerve::Vowelcounter::Client.new(
  api_key: "YOUR_API_KEY",
  debug: true
)
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

## Documentation

For more information, visit the [API Documentation](https://docs.apiverve.com/ref/vowelcounter?utm_source=rubygems&utm_medium=referral&utm_content=readme).

## Support

- Website: [https://apiverve.com/marketplace/vowelcounter?utm_source=ruby&utm_medium=referral&utm_content=readme](https://apiverve.com/marketplace/vowelcounter?utm_source=ruby&utm_medium=referral&utm_content=readme)
- Email: hello@apiverve.com

## License

This gem is available under the [MIT License](LICENSE).
