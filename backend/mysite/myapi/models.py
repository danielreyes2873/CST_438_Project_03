from django.db import models


# Create your models here.
class User(models.Model):
    username = models.CharField(max_length=60)
    password = models.CharField(max_length=60)
    firstname = models.CharField(max_length=60)
    lastname = models.CharField(max_length=60)

    def __str__(self):
        return self.username


class Quiz(models.Model):
    name = models.CharField(max_length=60, default="quiz")
    description = models.CharField(max_length=160, default="description")
    username = models.CharField(max_length=60)

    def __str__(self):
        return self.name


class Question(models.Model):
    question = models.CharField(max_length=160, default="Question Body")
    answer = models.CharField(max_length=250, default="Answer Body")
    quizname = models.CharField(max_length=60)

    def __str__(self):
        return self.question
