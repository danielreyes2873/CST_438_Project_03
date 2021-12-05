from django.shortcuts import render
from rest_framework import viewsets
from .serializers import UserSerializer, QuizSerializer, QuestionSerializer
from .models import User, Quiz, Question


# Create your views here.

# views.py


class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all().order_by('username')
    serializer_class = UserSerializer


class QuizViewSet(viewsets.ModelViewSet):
    queryset = Quiz.objects.all().order_by('name')
    serializer_class = QuizSerializer


class QuestionViewSet(viewsets.ModelViewSet):
    queryset = Question.objects.all().order_by('quizname')
    serializer_class = QuestionSerializer
