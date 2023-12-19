# Mobile Take-Home Assignment

### Mobile Tech Interview Prompt
###### ● For this exercise, you’ll be working on a small application (live) during the interview.
###### ● To speed things along, please prepare a basic app with the functionality described below, using your preferred libraries and patterns, and have it ready before the interview begins (you can create private Github project and invite your Noom interviewer to it at the start of the call).
###### ● The application to prepare is a simple food browser.
###### ● Create a simple screen with the search bar on top. When the user types in some text, you willquery the REST API described below. The REST API will return an array of food items.
###### ● For example, if the user types “chicken” into the search box, the REST API might return:
```json
[{
id: 0,
brand: "Journal Communications",
name: "BBQ Chicken Pizza",
calories: 119,
portion: 231
},
{
id: 17,
brand: "Journal Communications",
name: "Alice Springs Chicken",
calories: 134,
portion: 264
},
{
id: 41,
brand: "Starbucks",
name: "Chicken Alfredo",
calories: 873,
portion: 414
}]
```
###### ● You should display the names of the foods in a list below the search box.
###### ● Each time the user types a character into the search box, the list of foods below it should be updated with the latest foods returned by the REST API.
###### ● When the user taps on one of the names of the foods, display a simple message with the food name.
###### ● We don’t care about the prototype looking good — don’t spend time aligning pixels. We want to see how you organize your code, how you consider corner cases, and what your favorite libraries are.
###### ● During the interview you will add functionality to this food browser. The interview lasts between 30 and 60 minutes.

### REST API Details

###### ● The URL for the REST API is: https://uih0b7slze.execute-api.us-east-1.amazonaws.com/dev/search
###### ● A single query parameter called “kv” MUST be provided. If no “kv” query parameter is provided, then a non-JSON error message is returned.
###### ● The value for the “kv” query parameter must be at least 3 characters long. If “kv” is less than 3 characters long, then a non-JSON error message is returned.
###### ● The success response from this REST API is JSON. The response format is an array ofdictionaries. For example, if the URL is: ``https://uih0b7slze.execute-api.us-east-1.amazonaws.com/dev/search?kv=chicken`` Then the response will be:
```json
[
{
"id": 0,
"brand": "Journal Communications",
"name": "BBQ Chicken Pizza",
"calories": 119,
"portion": 231
},
{
"id": 17,
"brand": "Journal Communications",
"name": "Alice Springs Chicken",
"calories": 134,
"portion": 264
},
{
"id": 41,
"brand": "Starbucks",
"name": "Chicken Alfredo",
"calories": 873,
"portion": 414
},
...
]
```
###### ● More detail about the returned fields:
###### ○ calories is the number of calories in 100 grams of the food.
###### ○ portion is the number of grams in 1 portion of the food.
