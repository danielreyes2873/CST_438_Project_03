from django.db import models


# Create your models here.
class User(models.Model):
    username = models.CharField(max_length=60)
    password = models.CharField(max_length=60)

    def __str__(self):
        return self.username


class Quiz(models.Model):
    name = models.CharField(max_length=60, default="quiz")
    description = models.CharField(max_length=160, default="description")
    userID = models.ForeignKey(User, default=None, on_delete=models.CASCADE)

    def __str__(self):
        return self.name
