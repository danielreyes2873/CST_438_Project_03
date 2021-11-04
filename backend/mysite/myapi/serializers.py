# serializers.py
from rest_framework import serializers

from .models import User
from .models import Quiz


class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('username', 'password')


class QuizSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Quiz
        fields = ('name', 'description', 'userID')
