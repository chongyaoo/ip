# Grizzly User Guide

Grizzly is a simple Task management chatbot that helps you keep track of **todos, events, and deadlines**
It also supports saving of Tasks, so that you don't lose track of your saved items when restarting the chatbot. 

# Features

## Adding deadlines

Deadlines are Tasks that have to be completed before a specified Date/Time. 

Format: `deadline [Task] /by [Date/Time]`

Example: `deadline submitting CS2113 assignment /by 28th October 2359`

```
Got it. I've added this task:
[D][ ] submitting CS2113 assignment (by: 28th October 2359)
Now you have 1 tasks in the list
```

## Adding Todos

Todos are Tasks that do not have a specific Date/Time for completion. 

Format: `todo [Task]` 

Example: `todo go to the gym`

```
Got it. I've added this task:
[T][ ] go to the gym
Now you have 2 tasks in the list
```

## Adding Events

Events are Tasks that have a specified Date/Time of happening.

Format: `event [Task] /from [Date/Time]`

Example: `event CS2040C exam /from 6pm to 8pm`

```
Got it. I've added this task:
[E][ ] CS2040C exam (from: 6pm to 8pm)
Now you have 3 tasks in the list
```

## Mark/Unmark Tasks

Tasks can be marked/unmarked with an 'X' to denote completion. 

Format: `mark [idx]`, `unmark [idx]`

Example: `mark 2`

```
Nice! I've marked this task as done:
2.[T][X] go to the gym
```

## Delete Tasks

Tasks can be deleted from Grizzly as easily as they are added.

Format: `delete [idx]`

Example: `delete 2`

```
I've deleted this item from the list:
[T][X] go to the gym
Here are the tasks in your list:
1.[D][ ] submitting CS2113 assignment (by: 28th October 2359)
2.[E][ ] CS2040C exam (from: 6pm to 8pm)
```

## List Tasks

Tasks added to Grizzly so far can be viewed using the **print** or **list** command.

Format: `list`, `print`

```
Here are the tasks in your list:
1.[D][ ] submitting CS2113 assignment (by: 28th October 2359)
2.[E][ ] CS2040C exam (from: 6pm to 8pm)
```

## Find Tasks

Search for a Task that has been added to Grizzly using the **find** command to search keywords.

Format: `find [keyword]`

Example: `find assignment`

```
Here are the matching tasks in your list
1. [D][ ] submitting CS2113 assignment (by: 28th October 2359)
```
