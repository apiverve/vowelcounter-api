"""
Vowel Counter API - Basic Usage Example

This example demonstrates the basic usage of the Vowel Counter API.
API Documentation: https://docs.apiverve.com/ref/vowelcounter
"""

import os
import requests
import json

API_KEY = os.getenv('APIVERVE_API_KEY', 'YOUR_API_KEY_HERE')
API_URL = 'https://api.apiverve.com/v1/vowelcounter'

def call_vowelcounter_api():
    """
    Make a POST request to the Vowel Counter API
    """
    try:
        # Request body
        request_body &#x3D; {
    &#x27;text&#x27;: &#x27;The quick brown fox jumps over the lazy dog. This pangram contains every letter of the alphabet at least once.&#x27;
}

        headers = {
            'x-api-key': API_KEY,
            'Content-Type': 'application/json'
        }

        response = requests.post(API_URL, headers=headers, json=request_body)

        # Raise exception for HTTP errors
        response.raise_for_status()

        data = response.json()

        # Check API response status
        if data.get('status') == 'ok':
            print('✓ Success!')
            print('Response data:', json.dumps(data['data'], indent=2))
            return data['data']
        else:
            print('✗ API Error:', data.get('error', 'Unknown error'))
            return None

    except requests.exceptions.RequestException as e:
        print(f'✗ Request failed: {e}')
        return None

if __name__ == '__main__':
    print('📤 Calling Vowel Counter API...\n')

    result = call_vowelcounter_api()

    if result:
        print('\n📊 Final Result:')
        print(json.dumps(result, indent=2))
    else:
        print('\n✗ API call failed')
