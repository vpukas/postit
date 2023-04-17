import { render, screen, fireEvent } from '@testing-library/react';
import AddNote from './AddNote';

describe('AddNote', () => {
  test('renders textarea and save button', () => {
    render(<AddNote handleAddNote={() => { }} />);
    const textareaElement = screen.getByPlaceholderText('Type to add a note...');
    const saveButtonElement = screen.getByRole('button', { name: 'Save' });
    expect(textareaElement).toBeInTheDocument();
    expect(saveButtonElement).toBeInTheDocument();
  });

  test('updates the note content when typing in textarea', () => {
    render(<AddNote handleAddNote={() => { }} />);
    const textareaElement = screen.getByPlaceholderText('Type to add a note...');
    fireEvent.change(textareaElement, { target: { value: 'test' } });
    expect(textareaElement).toHaveValue('test');
  });

  test('calls handleAddNote with the note content when clicking the save button', () => {
    const handleAddNoteMock = jest.fn();
    render(<AddNote handleAddNote={handleAddNoteMock} />);
    const textareaElement = screen.getByPlaceholderText('Type to add a note...');
    const saveButtonElement = screen.getByRole('button', { name: 'Save' });
    fireEvent.change(textareaElement, { target: { value: 'test' } });
    fireEvent.click(saveButtonElement);
    expect(handleAddNoteMock).toHaveBeenCalledWith('test');
    expect(textareaElement).toHaveValue('');
  });
});