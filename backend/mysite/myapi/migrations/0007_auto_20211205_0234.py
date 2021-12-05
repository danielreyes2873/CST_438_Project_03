# Generated by Django 3.2.8 on 2021-12-05 02:34

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myapi', '0006_question'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='question',
            name='quizID',
        ),
        migrations.RemoveField(
            model_name='quiz',
            name='userID',
        ),
        migrations.AddField(
            model_name='question',
            name='quizname',
            field=models.CharField(default='quiz', max_length=60),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='quiz',
            name='username',
            field=models.CharField(default='username', max_length=60),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='user',
            name='firstname',
            field=models.CharField(default='firstname', max_length=60),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='user',
            name='lastname',
            field=models.CharField(default='lastname', max_length=60),
            preserve_default=False,
        ),
    ]
