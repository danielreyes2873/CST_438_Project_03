# Generated by Django 3.2.7 on 2021-11-04 17:18

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('myapi', '0003_delete_quiz'),
    ]

    operations = [
        migrations.CreateModel(
            name='Quiz',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(default='quiz', max_length=60)),
                ('description', models.CharField(default='description', max_length=160)),
                ('userID', models.ForeignKey(default=None, on_delete=django.db.models.deletion.CASCADE, to='myapi.user')),
            ],
        ),
    ]
